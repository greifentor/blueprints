package de.ollie.blueprints.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.ollie.blueprints.controller.model.EntryDTO;
import de.ollie.blueprints.service.EntryService;

@RestController
public class EntryController {

	@Autowired
	private EntryService entryService;

	@GetMapping(path = "/api/v1/entries", produces = { "application/json" })
	@ResponseBody
	public List<EntryDTO> readEntries(@RequestParam(name = "from", required = false) String fromStr,
			@RequestParam(name = "til", required = false) String tilStr) {
		LocalDate from = fromStr == null ? LocalDate.of(1900, 1, 1) : LocalDate.parse(fromStr);
		LocalDate til = tilStr == null ? LocalDate.of(2999, 12, 31) : LocalDate.parse(tilStr);
		List<EntryDTO> l = new ArrayList<>();
		this.entryService.readEntries(from, til).forEach(eso -> {
			l.add(new EntryDTO().setChangedOnDate(eso.getChangedOnDate()).setDescription(eso.getDescription())
					.setId(eso.getId())); // (1)
		});
		return l;
	}

}

// (1) A converter class should be used here :o)