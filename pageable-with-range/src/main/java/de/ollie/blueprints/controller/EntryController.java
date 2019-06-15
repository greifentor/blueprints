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
	public List<EntryDTO> readEntries(@RequestParam(name = "from") String fromStr,
			@RequestParam(name = "til") String tilStr) {
		LocalDate from = LocalDate.parse(fromStr);
		LocalDate til = LocalDate.parse(tilStr);
		List<EntryDTO> l = new ArrayList<>();
		this.entryService.readEntries(from, til).forEach(eso -> {
			l.add(new EntryDTO().setChangedOnDate(eso.getChangedOnDate()).setDescription(eso.getDescription())
					.setId(eso.getId()));
		});
		return l;
	}

}