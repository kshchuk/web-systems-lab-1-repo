package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void testGetProduct() throws Exception {
		// Test the endpoint for a specific productId
		mockMvc.perform(get("/products/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", is("1")))
				.andExpect(jsonPath("$.productName", is("1 name")));
	}

	@Test
	public void testGetProducts() throws Exception {
		// Test to ensure the getProducts method works correctly
		mockMvc.perform(get("/products")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
