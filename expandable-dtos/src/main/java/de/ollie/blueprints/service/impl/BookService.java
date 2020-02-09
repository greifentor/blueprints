package de.ollie.blueprints.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.blueprints.service.model.Book;
import de.ollie.blueprints.service.model.Rack;
import de.ollie.blueprints.service.model.Room;

@Service
public class BookService {

	public Optional<Book> findById(long id) {
		if (id == 42) {
			Room room = new Room(1L, "Room 1");
			Rack rack = new Rack(2L, "Rack A", room);
			Book book = new Book(3L, "The Lord of the Rings", rack);
			return Optional.of(book);
		}
		return Optional.empty();
	}

}