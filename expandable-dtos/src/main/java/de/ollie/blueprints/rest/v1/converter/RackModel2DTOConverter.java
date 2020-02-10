package de.ollie.blueprints.rest.v1.converter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.blueprints.rest.v1.dto.RackDTO;
import de.ollie.blueprints.rest.v1.dto.ReferenceDTO;
import de.ollie.blueprints.service.model.Rack;

@Named
public class RackModel2DTOConverter extends AbstractModel2DtoConverter {

	@Inject
	private RoomModel2DTOConverter roomModel2DTOConverter;

	public RackDTO convert(Rack model, List<String> toExpand) {
		if (model == null) {
			return null;
		}
		return new RackDTO(//
				model.getId(), //
				model.getName(), //
				this.roomModel2DTOConverter.getRoomReferenceDTO(
						this.roomModel2DTOConverter.convert(model.getRoom(), getToExpand("room", toExpand)),
						isToExpand("room", toExpand)) //
		);
	}

	public ReferenceDTO<Long, RackDTO> getRackReferenceDTO(RackDTO rack, boolean isToExpand) {
		if (rack == null) {
			return null;
		}
		return new ReferenceDTO<Long, RackDTO>( //
				rack.getId(), //
				"/api/v1/racks/" + rack.getId(), //
				(isToExpand ? rack : null)//
		);
	}

}