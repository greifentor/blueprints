package de.ollie.blueprints.rest.v1.converter;

import java.util.List;

import javax.inject.Named;

import de.ollie.blueprints.rest.v1.dto.RoomDTO;
import de.ollie.blueprints.service.model.Room;

@Named
public class RoomModel2DTOConverter {

	public RoomDTO convert(Room model, List<String> toExpand) {
		if (model == null) {
			return null;
		}
		return new RoomDTO(//
				model.getId(), //
				model.getName() //
		);
	}

}