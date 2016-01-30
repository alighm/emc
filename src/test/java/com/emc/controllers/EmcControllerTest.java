package com.emc.controllers;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.emc.EmcApplication;
import com.emc.api.models.request.FibonacciRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EmcApplication.class)
@WebAppConfiguration
public class EmcControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	ObjectWriter ow;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	}

	@Test
	public void validFibonnaciRequestTest() throws Exception {
		FibonacciRequest request = new FibonacciRequest();
		request.setSize(5);

		mockMvc.perform(post("/fibonacci")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ow.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(content().string("[0,1,1,2,3]"));
	}

	@Test
	public void invalidFibonnaciRequestTest() throws Exception {
		FibonacciRequest request = new FibonacciRequest();
		request.setSize(-1);

		mockMvc.perform(post("/fibonacci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ow.writeValueAsString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors.['size.invalid']", 
						equalToIgnoringCase("size is lesser than 0. Please provide a positive number")));
	}
}