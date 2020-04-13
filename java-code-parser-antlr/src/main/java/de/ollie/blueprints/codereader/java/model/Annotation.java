package de.ollie.blueprints.codereader.java.model;

import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for annotation data.
 *
 * @author ollie (13.04.2020)
 */
@Accessors(chain = true)
@Data
@Generated
public class Annotation {

	private String name;
	private String value;

}