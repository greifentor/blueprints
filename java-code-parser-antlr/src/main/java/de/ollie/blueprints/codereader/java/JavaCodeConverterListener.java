package de.ollie.blueprints.codereader.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import de.ollie.blueprints.codereader.java.antlr.Java8BaseListener;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.AnnotationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ClassDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ClassModifierContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.CompilationUnitContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ElementValueContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ElementValuePairContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ElementValuePairListContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.ImportDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.MarkerAnnotationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.NormalAnnotationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.NormalClassDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.PackageDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.PackageNameContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.PackageOrTypeNameContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.SingleElementAnnotationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.SingleStaticImportDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.SingleTypeImportDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.StaticImportOnDemandDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.TypeDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.TypeImportOnDemandDeclarationContext;
import de.ollie.blueprints.codereader.java.antlr.Java8Parser.TypeNameContext;
import de.ollie.blueprints.codereader.java.model.Annotation;
import de.ollie.blueprints.codereader.java.model.ClassDeclaration;
import de.ollie.blueprints.codereader.java.model.CompilationUnit;
import de.ollie.blueprints.codereader.java.model.ElementValuePair;
import de.ollie.blueprints.codereader.java.model.ImportDeclaration;
import de.ollie.blueprints.codereader.java.model.Modifier;

/**
 * The listener which converts the compilation unit from the parser tree walk.
 *
 * @author ollie (13.04.2020)
 */
public class JavaCodeConverterListener extends Java8BaseListener {

	private CompilationUnit compilationUnit;

	public JavaCodeConverterListener(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		printChildren("", ctx);
		readPackageDeclaration(ctx);
		readImportDeclarations(ctx);
		readClassTypeDeclarations(ctx);
	}

	private void readPackageDeclaration(CompilationUnitContext ctx) {
		findChildByClass(ctx, PackageDeclarationContext.class).ifPresent(//
				pdc -> findChildByClass(pdc, PackageNameContext.class).ifPresent(//
						pnc -> compilationUnit.setPackageName(pnc.getText()) //
				) //
		);
	}

	private void readImportDeclarations(CompilationUnitContext ctx) {
		for (ImportDeclarationContext idc : findChildsByClass(ctx, ImportDeclarationContext.class)) {
			for (SingleStaticImportDeclarationContext ssidc : findChildsByClass(idc,
					SingleStaticImportDeclarationContext.class)) {
				findChildByClass(ssidc, TypeNameContext.class).ifPresent( //
						tnc -> {
							List<TerminalNodeImpl> tnis = findChildsByClass(ssidc, TerminalNodeImpl.class);
							compilationUnit.addImportDeclarations( //
									new ImportDeclaration() //
											.setImportedObject(tnis.get(tnis.size() - 2).getText()) //
											.setQualifiedName(tnc.getText()) //
											.setStaticImport(true));
						} //
				);
			}
			for (StaticImportOnDemandDeclarationContext sioddc : findChildsByClass(idc,
					StaticImportOnDemandDeclarationContext.class)) {
				findChildByClass(sioddc, TypeNameContext.class).ifPresent( //
						tnc -> compilationUnit.addImportDeclarations( //
								new ImportDeclaration() //
										.setQualifiedName(tnc.getText()) //
										.setSingleTypeImport(false) //
										.setStaticImport(true) //
						) //
				);
			}
			for (SingleTypeImportDeclarationContext stidc : findChildsByClass(idc,
					SingleTypeImportDeclarationContext.class)) {
				findChildByClass(stidc, TypeNameContext.class).ifPresent( //
						tnc -> compilationUnit.addImportDeclarations( //
								new ImportDeclaration() //
										.setQualifiedName(tnc.getText()) //
						) //
				);
			}
			for (TypeImportOnDemandDeclarationContext tioddc : findChildsByClass(idc,
					TypeImportOnDemandDeclarationContext.class)) {
				findChildByClass(tioddc, PackageOrTypeNameContext.class).ifPresent( //
						potnc -> compilationUnit.addImportDeclarations( //
								new ImportDeclaration() //
										.setQualifiedName(potnc.getText()) //
										.setSingleTypeImport(false) //
						) //
				);
			}
		}
	}

	private void readClassTypeDeclarations(CompilationUnitContext ctx) {
		for (TypeDeclarationContext tdc : findChildsByClass(ctx, TypeDeclarationContext.class)) {
			for (ClassDeclarationContext cdc : findChildsByClass(tdc, ClassDeclarationContext.class)) {
				for (NormalClassDeclarationContext ncdc : findChildsByClass(cdc, NormalClassDeclarationContext.class)) {
					findNextChildByClassAndContent(ncdc, TerminalNodeImpl.class, "class").ifPresentOrElse( //
							tni -> {
								ClassDeclaration classDeclaration = new ClassDeclaration() //
										.addAnnotations(getAnnotations(ncdc)) //
										.addModifiers(getClassModifiers(ncdc)) //
										.setName(tni.getText()) //
								;
								this.compilationUnit.addTypeDeclarations(classDeclaration);
							}, //
							() -> {
								throw new JavaCodeConverterException("ERROR: not found: 'class' in: " + ncdc.getText(),
										null);
							});
				}
			}
		}
	}

	private Annotation[] getAnnotations(ParserRuleContext prc) {
		List<Annotation> l = new ArrayList<>();
		for (ClassModifierContext cmc : findChildsByClass(prc, ClassModifierContext.class)) {
			for (AnnotationContext ac : findChildsByClass(cmc, AnnotationContext.class)) {
				findChildByClass(ac, MarkerAnnotationContext.class).ifPresent( //
						mac -> findChildByClass(mac, TypeNameContext.class).ifPresent( //
								tnc -> l.add(new Annotation().setName(tnc.getText())) //
						) //
				);
				findChildByClass(ac, SingleElementAnnotationContext.class).ifPresent( //
						seac -> findChildByClass(seac, TypeNameContext.class).ifPresent( //
								tnc -> l.add(new Annotation().setName(tnc.getText()).setValue( //
										findChildByClass(seac, ElementValueContext.class) //
												.map(ElementValueContext::getText) //
												.get() //
								)) //
						) //
				);
				findChildByClass(ac, NormalAnnotationContext.class).ifPresent( //
						nac -> findChildByClass(nac, TypeNameContext.class).ifPresent( //
								tnc -> {
									Annotation annotation = new Annotation().setName(tnc.getText());
									l.add(annotation);
									findChildByClass(nac, ElementValuePairListContext.class).ifPresent( //
											evplc -> findChildsByClass(evplc, ElementValuePairContext.class) //
													.forEach(evpc -> {
														String key = findChildByClass(evpc, TerminalNodeImpl.class)
																.get().getText();
														String value = findChildByClass(evpc, ElementValueContext.class)
																.get().getText();
														annotation.addElementValues(new ElementValuePair() //
																.setKey(key) //
																.setValue(value) //
														);
													}) //
									);
								}) //
				);
			}
		}
		return l.toArray(new Annotation[0]);
	}

	private Modifier[] getClassModifiers(ParserRuleContext prc) {
		List<Modifier> l = new ArrayList<>();
		for (ClassModifierContext cmc : findChildsByClass(prc, ClassModifierContext.class)) {
			for (TerminalNodeImpl tni : findChildsByClass(cmc, TerminalNodeImpl.class)) {
				String value = tni.getText();
				if (value.equals("abstract")) {
					l.add(Modifier.ABSTRACT);
				} else if (value.equals("public")) {
					l.add(Modifier.PUBLIC);
				}
			}
		}
		return l.toArray(new Modifier[0]);
	}

	private static <T> Optional<T> findChildByClassAndContent(ParserRuleContext ctx, Class<T> cls, String content) {
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if ((ctx.getChild(i).getClass() == cls) && ctx.getChild(i).getText().equals(content)) {
				return Optional.of((T) ctx.getChild(i));
			}
		}
		return Optional.empty();
	}

	private static <T> Optional<T> findNextChildByClassAndContent(ParserRuleContext ctx, Class<T> cls, String content) {
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if ((ctx.getChild(i).getClass() == cls) && ctx.getChild(i).getText().equals(content)) {
				return Optional.of((T) ctx.getChild(i + 1));
			}
		}
		return Optional.empty();
	}

	private static <T> Optional<T> findChildByClass(ParseTree ctx, Class<T> cls) {
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if (ctx.getChild(i).getClass() == cls) {
				return Optional.of((T) ctx.getChild(i));
			}
		}
		return Optional.empty();
	}

	private static <T> List<T> findChildsByClass(ParseTree ctx, Class<T> cls) {
		List<T> l = new ArrayList<>(/* ctx.getChildCount() */);
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if (ctx.getChild(i).getClass() == cls) {
				l.add((T) ctx.getChild(i));
			}
		}
		return l;
	}

	private static <T> T getContext(ParserRuleContext ctx, Class<T> cls, String content) {
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if ((ctx.getChild(i).getClass() == cls) && ctx.getChild(i).getText().startsWith(content)) {
				return (T) ctx.getChild(i);
			}
		}
		for (int i = 0, leni = ctx.getChildCount(); i < leni; i++) {
			if (ctx.getChild(i) instanceof ParserRuleContext) {
				T result = getContext((ParserRuleContext) ctx.getChild(i), cls, content);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	static void printChildren(String indent, ParserRuleContext prc) {
		System.out.println(indent + " > " + prc.getClass().getSimpleName() + " = " + prc.getText());
		for (int i = 0, leni = prc.getChildCount(); i < leni; i++) {
			if (prc.getChild(i) instanceof ParserRuleContext) {
				printChildren("  " + indent, (ParserRuleContext) prc.getChild(i));
			} else {
				System.out.println(indent + " - " + i + " > " + prc.getChild(i).getClass().getSimpleName() + " > "
						+ prc.getChild(i).getText());
			}
		}
	}

	static void printChildrenFlat(String indent, ParserRuleContext prc) {
		System.out.println(indent + " > " + prc.getClass().getSimpleName() + " = " + prc.getText());
		for (int i = 0, leni = prc.getChildCount(); i < leni; i++) {
			System.out.println(indent + " - " + i + " > " + prc.getChild(i).getClass().getSimpleName() + " > "
					+ prc.getChild(i).getText());
		}
	}

}