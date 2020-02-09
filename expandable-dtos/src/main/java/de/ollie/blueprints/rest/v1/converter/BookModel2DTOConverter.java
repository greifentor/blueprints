package de.ollie.blueprints.rest.v1.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.blueprints.rest.v1.dto.BookDTO;
import de.ollie.blueprints.service.model.Book;

@Named
public class BookModel2DTOConverter {

	@Inject
	private RackModel2DTOConverter rackModel2DTOConverter;

	public BookDTO convert(Book model, List<String> toExpand) {
		if (model == null) {
			return null;
		}
		return new BookDTO(//
				model.getId(), //
				model.getTitle(), //
				(isToExpand("rack", toExpand)
						? this.rackModel2DTOConverter.convert(model.getRack(), getToExpand("rack", toExpand))
						: model.getRack().getId()) //
		);
	}

	private boolean isToExpand(String attributeName, List<String> toExpand) {
		return toExpand //
				.stream() //
				.anyMatch(expand -> expand.toLowerCase().startsWith(attributeName.toLowerCase() + ".")
						|| expand.equalsIgnoreCase(attributeName)) // ) //
		;
	}

	private List<String> getToExpand(String attributeName, List<String> toExpand) {
		return toExpand //
				.stream() //
				.filter(expand -> expand.toLowerCase().startsWith(attributeName + ".")) //
				.map(expand -> expand.replace(attributeName + ".", "")) //
				.collect(Collectors.toList()) //
		;
	}

}