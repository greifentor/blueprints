package de.ollie.blueprints.rest.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Generated
@NoArgsConstructor
public class BookDTO {

	private Long id;
	private String title;
	private ReferenceDTO<Long, RackDTO> rack;

}