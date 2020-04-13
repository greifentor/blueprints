package de.ollie.blueprints.codereader.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.blueprints.codereader.java.model.ClassDeclaration;
import de.ollie.blueprints.codereader.java.model.ClassOrInterfaceModifier;
import de.ollie.blueprints.codereader.java.model.CompilationUnit;
import de.ollie.blueprints.codereader.java.model.ImportDeclaration;

@ExtendWith(MockitoExtension.class)
public class JavaCodeConverterTest {

	@InjectMocks
	private JavaCodeConverter unitUnderTest;

	@DisplayName("Returns a null value, if a null value is passed")
	@Test
	void nullValuePassed_ReturnsANullValue() {
		assertNull(unitUnderTest.convert(null), "null value expected for a passed null value.");
	}

	@DisplayName("Returns an empty compilation unit, if an empty string is passed")
	@Test
	void emptyStringPassed_ReturnsAnEmptyCompilationUnit() {
		// Prepare
		CompilationUnit expected = new CompilationUnit();
		// Run
		CompilationUnit returned = unitUnderTest.convert("");
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a simple class file content.")
	@Test
	void simpleClassFileContentPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "class ASimpleClass {\n" //
				+ "}";
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.setName("ASimpleClass") //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with class modifier 'public'.")
	@Test
	void simpleClassFileContentWithClassModifierPublicPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "public class ASimpleClass {\n" //
				+ "}";
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with class modifier 'public' and "
			+ "'abstract'.")
	@Test
	void simpleClassFileContentWithClassModifierPublicAndAbstractPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "public abstract class ASimpleClass {\n" //
				+ "}";
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC, //
										ClassOrInterfaceModifier.ABSTRACT //
								) //
								.setName("ASimpleClass") //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with two classes.")
	@Test
	void simpleClassFileContentWithTwoClassesPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "public abstract class ASimpleClass {\n" //
				+ "}\n" //
				+ "\n" //
				+ "class AFriendClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC, //
										ClassOrInterfaceModifier.ABSTRACT //
								) //
								.setName("ASimpleClass"), //
						new ClassDeclaration() //
								.setName("AFriendClass") //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with package declaration.")
	@Test
	void simpleClassFileContentWithAPackageDeclarationPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "package fantasy.pack.age;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.setPackageName("fantasy.pack.age") //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with import declaration for a single "
			+ "class.")
	@Test
	void simpleClassFileContentWithASingleClassImportDeclarationPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "import fantasy.pack.age.AClass;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.addImportDeclarations( //
						new ImportDeclaration().setQualifiedName("fantasy.pack.age.AClass") //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with import declaration for a whole "
			+ "package.")
	@Test
	void simpleClassFileContentWithAWholePackageImportDeclarationPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "import fantasy.pack.age.*;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.addImportDeclarations( //
						new ImportDeclaration() //
								.setQualifiedName("fantasy.pack.age") //
								.setSingleTypeImport(false) //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with static import declaration for a single "
			+ "class.")
	@Test
	void simpleClassFileContentWithAStaticSingleClassImportDeclarationPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "import static fantasy.pack.age.AClass.aMethod;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.addImportDeclarations( //
						new ImportDeclaration() //
								.setImportedObject("aMethod") //
								.setQualifiedName("fantasy.pack.age.AClass") //
								.setStaticImport(true) //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with static import declaration for a "
			+ "whole package.")
	@Test
	void simpleClassFileContentWithAStaticWholePackageImportDeclarationPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "import static fantasy.pack.age.AClass.*;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.addImportDeclarations( //
						new ImportDeclaration() //
								.setQualifiedName("fantasy.pack.age.AClass") //
								.setSingleTypeImport(false) //
								.setStaticImport(true) //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

	@DisplayName("Returns correct compilation unit passing a class file content with static import declaration for a "
			+ "whole package.")
	@Test
	void simpleClassFileContentWithTwoPackageImportDeclarationsPassed_ReturnsACorrectCompilationUnit() {
		// Prepare
		String simpleClassFileContent = "" //
				+ "import static fantasy.pack.age.AClass.*;\n" //
				+ "import alles.hier.von.*;\n" //
				+ "import alles.hier.von.auch.*;\n" //
				+ "\n" //
				+ "public class ASimpleClass {\n" //
				+ "}\n" //
		;
		CompilationUnit expected = new CompilationUnit() //
				.addTypeDeclarations( //
						new ClassDeclaration() //
								.addClassOrInterfaceModifiers( //
										ClassOrInterfaceModifier.PUBLIC //
								) //
								.setName("ASimpleClass") //
				) //
				.addImportDeclarations( //
						new ImportDeclaration() //
								.setQualifiedName("fantasy.pack.age.AClass") //
								.setSingleTypeImport(false) //
								.setStaticImport(true), //
						new ImportDeclaration() //
								.setQualifiedName("alles.hier.von") //
								.setSingleTypeImport(false), //
						new ImportDeclaration() //
								.setQualifiedName("alles.hier.von.auch") //
								.setSingleTypeImport(false) //
				) //
		;
		// Run
		CompilationUnit returned = unitUnderTest.convert(simpleClassFileContent);
		// Check
		assertEquals(expected, returned);
	}

}