package de.ollie.blueprints.rest.v1.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.ollie.blueprints.rest.v1.dto.BookDTO;

public class RESTClient {

	public void read() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/books";
		ResponseEntity<BookDTO> response = restTemplate.getForEntity(url + "/42?expand=rack", BookDTO.class);
		System.out.println(response);
	}

	public static void main(String[] args) {
		new RESTClient().read();
	}

}