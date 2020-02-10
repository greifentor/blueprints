package de.ollie.blueprints.rest.v1.converter;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractModel2DtoConverter {

	protected boolean isToExpand(String attributeName, List<String> toExpand) {
		return toExpand //
				.stream() //
				.anyMatch(expand -> expand.toLowerCase().startsWith(attributeName.toLowerCase() + ".")
						|| expand.equalsIgnoreCase(attributeName)) //
		;
	}

	protected List<String> getToExpand(String attributeName, List<String> toExpand) {
		return toExpand //
				.stream() //
				.filter(expand -> expand.toLowerCase().startsWith(attributeName + ".")) //
				.map(expand -> expand.replace(attributeName + ".", "")) //
				.collect(Collectors.toList()) //
		;
	}

}