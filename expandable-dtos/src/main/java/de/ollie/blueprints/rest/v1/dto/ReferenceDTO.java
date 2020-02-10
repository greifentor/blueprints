package de.ollie.blueprints.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

/**
 * A DTO for object references
 * 
 * @param <K> The class of the objects key.
 * @param <T> The type of the expanded reference (usually the DTO of the referenced object).
 *
 * @author ollie (10.02.2020)
 */
@AllArgsConstructor
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferenceDTO<K, T> {

	private K id;
	private String link;
	private T expanded;

}