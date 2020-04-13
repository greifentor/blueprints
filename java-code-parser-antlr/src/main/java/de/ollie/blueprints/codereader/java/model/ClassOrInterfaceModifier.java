package de.ollie.blueprints.codereader.java.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for class or interface modifiers.
 *
 * @author ollie (13.04.2020)
 */
@Accessors(chain = true)
@Data
@Generated
public class ClassOrInterfaceModifier {

	public static final ClassOrInterfaceModifier ABSTRACT = new ClassOrInterfaceModifier()
			.setType(ClassOrInterfaceModifierType.ABSTRACT);
	public static final ClassOrInterfaceModifier PUBLIC = new ClassOrInterfaceModifier()
			.setType(ClassOrInterfaceModifierType.PUBLIC);

	private ClassOrInterfaceModifierType type;

}