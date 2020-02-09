package de.ollie.blueprints.rest.v1.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.blueprints.rest.v1.dto.RackDTO;
import de.ollie.blueprints.service.model.Rack;

@Named
public class RackModel2DTOConverter {

	@Inject
	private RoomModel2DTOConverter roomModel2DTOConverter;

	public RackDTO convert(Rack model, List<String> toExpand) {
		if (model == null) {
			return null;
		}
		return new RackDTO(//
				model.getId(), //
				model.getName(), //
				(isToExpand("room", toExpand)
						? this.roomModel2DTOConverter.convert(model.getRoom(), getToExpand("room", toExpand))
						: model.getRoom().getId()) //
		);
	}

	private boolean isToExpand(String attributeName, List<String> toExpand) {
		return toExpand //
				.stream() //
				.anyMatch(expand -> expand.toLowerCase().startsWith(attributeName.toLowerCase() + ".")
						|| expand.equalsIgnoreCase(attributeName)) //
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