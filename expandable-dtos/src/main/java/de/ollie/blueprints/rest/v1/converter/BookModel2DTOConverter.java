package de.ollie.blueprints.rest.v1.converter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.blueprints.rest.v1.dto.BookDTO;
import de.ollie.blueprints.service.model.Book;

@Named
public class BookModel2DTOConverter extends AbstractModel2DtoConverter {

	@Inject
	private RackModel2DTOConverter rackModel2DTOConverter;

	public BookDTO convert(Book model, List<String> toExpand) {
		if (model == null) {
			return null;
		}
		return new BookDTO(//
				model.getId(), //
				model.getTitle(), //
				this.rackModel2DTOConverter.getRackReferenceDTO(
						this.rackModel2DTOConverter.convert(model.getRack(), getToExpand("rack", toExpand)),
						isToExpand("rack", toExpand)) //
		);
	}

}