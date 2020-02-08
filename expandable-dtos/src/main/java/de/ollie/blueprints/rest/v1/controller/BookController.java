package de.ollie.blueprints.rest.v1.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ollie.blueprints.rest.v1.dto.BookDTO;

@RestController
public class BookController {

	static final String BOOKS_URL = "api/v1/books";

	@GetMapping(BookController.BOOKS_URL + "/{id}")
	public ResponseEntity<BookDTO> findById(@PathParam("id") Long id) {
		return ResponseEntity.ok(new BookDTO(42L, "BLA", 43L));
	}

}