package de.ollie.blueprints;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import de.ollie.blueprints.rest.v1.controller.BookController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationITest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void urlForAnExistingBookCalled_ReturnsTheBooksData() throws Exception {
		mockMvc.perform(get("/" + BookController.BOOKS_URL + "/{id}", 42L)) //
				.andExpect(status().isOk()) //
				.andExpect(content().string("{\"id\":3,\"title\":\"The Lord of the Rings\",\"rack\":2}")) //
		;
	}

	@Test
	public void urlForAnExistingBookWithRackExpandCalled_ReturnsTheBooksData() throws Exception {
		mockMvc.perform(get("/" + BookController.BOOKS_URL + "/{id}?expand=rack", 42L)) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(
						"{\"id\":3,\"title\":\"The Lord of the Rings\",\"rack\":{\"id\":2,\"name\":\"Rack A\",\"room\":1}}")) //
		;
	}

	@Test
	public void urlForAnExistingBookWithRoomExpandCalled_ReturnsTheBooksData() throws Exception {
		mockMvc.perform(get("/" + BookController.BOOKS_URL + "/{id}?expand=rack.room", 42L)) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(
						"{\"id\":3,\"title\":\"The Lord of the Rings\",\"rack\":{\"id\":2,\"name\":\"Rack A\",\"room\":{\"id\":1,\"name\":\"Room 1\"}}}")) //
		;
	}

	@Test
	public void urlForAnUnexistingBookCalled_ReturnsNotFound() throws Exception {
		mockMvc.perform(get("/" + BookController.BOOKS_URL + "/{id}", 43L)) //
				.andExpect(status().isNotFound()) //
		;
	}

}