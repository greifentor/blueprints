package de.ollie.blueprints.codereader.java.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for class declaration data.
 *
 * @author ollie (13.04.2020)
 */
@Accessors(chain = true)
@Data
@Generated
public class ClassDeclaration implements TypeDeclaration {

	private String name;
	private List<ClassOrInterfaceModifier> classOrInterfaceModifiers = new ArrayList<>();

	public ClassDeclaration addClassOrInterfaceModifiers(ClassOrInterfaceModifier... classOrInterfaceModifiers) {
		for (ClassOrInterfaceModifier classOrInterfaceModifier : classOrInterfaceModifiers) {
			this.classOrInterfaceModifiers.add(classOrInterfaceModifier);
		}
		return this;
	}

}