package de.ollie.blueprints.rest.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ollie.blueprints.rest.v1.dto.BookDTO;
import de.ollie.blueprints.service.impl.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	static final String BOOKS_URL = "api/v1/books";

	@GetMapping(BookController.BOOKS_URL + "/{id}")
	public ResponseEntity<BookDTO> findById( //
			@PathVariable("id") long id, //
			@RequestParam(name = "expand", required = false) String expand //
	) {
		return this.bookService.findById(id) //
				.map(book -> ResponseEntity.ok(new BookDTO(42L, "BLA", 43L))) //
				.orElse(ResponseEntity.notFound().build()) //
		;
	}

}