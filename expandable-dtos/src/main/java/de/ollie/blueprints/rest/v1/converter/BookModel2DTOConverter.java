package de.ollie.blueprints.rest.v1.converter;

import de.ollie.blueprints.rest.v1.dto.BookDTO;
import de.ollie.blueprints.service.model.Book;

public class BookModel2DTOConverter {

	public BookDTO convert(Book model) {
		if (model == null) {
			return null;
		}
		return new BookDTO(//
				model.getId(), //
				model.getTitle(), //
				model.getRack().getId() //
		);
	}

}
