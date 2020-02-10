package de.ollie.blueprints.rest.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@AllArgsConstructor
@Data
@Generated
public class BookDTO {

	private Long id;
	private String title;
	private ReferenceDTO<Long, RackDTO> rack;

}