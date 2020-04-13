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

	private List<Annotation> annotations = new ArrayList<>();
	private List<Modifier> modifiers = new ArrayList<>();
	private String name;

	public ClassDeclaration addAnnotations(Annotation... annotations) {
		for (Annotation annotation : annotations) {
			this.annotations.add(annotation);
		}
		return this;
	}

	public ClassDeclaration addModifiers(Modifier... modifiers) {
		for (Modifier modifier : modifiers) {
			this.modifiers.add(modifier);
		}
		return this;
	}

}