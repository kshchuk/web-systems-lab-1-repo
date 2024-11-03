//package com.example.demo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ProductController.class)
//public class ProductControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private ProductRepository productRepository;
//
//	@BeforeEach
//	public void setUp() {
//		Product product = new Product("1 name");
//		product.setId(1);
//
//		// Mock findById
//		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
//		Mockito.when(productRepository.findById(999)).thenReturn(Optional.empty());
//
//		// Mock findAll
//		Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
//	}
//
//	@Test
//	public void testGetProduct_ExistingProduct() throws Exception {
//		mockMvc.perform(get("/products/1")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.productId", is("1")))
//				.andExpect(jsonPath("$.productName", is("1 name")));
//	}
//
//	@Test
//	public void testGetProduct_NonExistingProduct() throws Exception {
//		mockMvc.perform(get("/products/999")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.error", is("Product not found")));
//	}
//
//	@Test
//	public void testGetProducts() throws Exception {
//		mockMvc.perform(get("/products")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].id", is(1)))
//				.andExpect(jsonPath("$[0].name", is("1 name")));
//	}
//}
