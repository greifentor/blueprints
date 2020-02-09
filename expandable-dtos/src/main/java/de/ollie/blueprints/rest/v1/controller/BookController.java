package de.ollie.blueprints.rest.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ollie.blueprints.rest.v1.converter.BookModel2DTOConverter;
import de.ollie.blueprints.rest.v1.dto.BookDTO;
import de.ollie.blueprints.service.impl.BookService;

@RestController
public class BookController {

	@Inject
	private BookService bookService;
	@Inject
	private BookModel2DTOConverter bookModel2DTOConverter;

	public static final String BOOKS_URL = "api/v1/books";

	@GetMapping(BookController.BOOKS_URL + "/{id}")
	public ResponseEntity<BookDTO> findById( //
			@PathVariable("id") long id, //
			@RequestParam(name = "expand", required = false) String expand //
	) {
		List<String> toExpand = getToExpand(expand);
		return this.bookService.findById(id) //
				.map(model -> ResponseEntity.ok(this.bookModel2DTOConverter.convert(model, toExpand))) //
				.orElse(ResponseEntity.notFound().build()) //
		;
	}

	private List<String> getToExpand(String expand) {
		return split(expand) //
				.stream() //
				.map(toExpand -> toExpand.replace("book.", "")) //
				.collect(Collectors.toList()) //
		;
	}

	private List<String> split(String expand) {
		List<String> result = new ArrayList<>();
		if (expand != null) {
			StringTokenizer st = new StringTokenizer(expand, ",");
			while (st.hasMoreTokens()) {
				result.add(st.nextToken().trim());
			}
		}
		return result;
	}

}