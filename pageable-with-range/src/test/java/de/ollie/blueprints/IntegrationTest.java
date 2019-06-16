package de.ollie.blueprints;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BluePrintsApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void entries_PassARange_ReturnsTheEntriesInTheRange() throws Exception {
		this.mvc.perform(get("/api/v1/entries").param("from", "2019-06-03").param("til", "2019-06-10")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", equalTo("three")))
				.andExpect(jsonPath("$[4].description", equalTo("seven")));
	}

	@Test
	public void entries_PassNoRange_ReturnsTheFirstEntries() throws Exception {
		this.mvc.perform(get("/api/v1/entries").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", equalTo("one")))
				.andExpect(jsonPath("$[4].description", equalTo("five")));
	}

	@Test
	public void entries_PassFromOnly_ReturnsTheFirstEntriesInTheRange() throws Exception {
		this.mvc.perform(get("/api/v1/entries").param("from", "2019-06-03").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", equalTo("three")))
				.andExpect(jsonPath("$[4].description", equalTo("seven")));
	}

	@Test
	public void entries_PassTilOnly_ReturnsTheFirstEntries() throws Exception {
		this.mvc.perform(get("/api/v1/entries").param("til", "2019-06-03").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].description", equalTo("one")))
				.andExpect(jsonPath("$[2].description", equalTo("three")));
	}

}