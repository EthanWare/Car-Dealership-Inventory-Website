package com.ethan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private MockMvc mock;

	@Test
	public void givenReadCars_whenCarsInDatabase_thenReadCarsSuccessful() throws Exception {
		mock.perform(get("/cars/read").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void givenReadCarsByYear_whenCarsInDatabase_thenReadCarsByYearSuccessful() throws Exception {
		mock.perform(get("/cars/read/year/2005"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void givenReadCarsByMake_whenCarsInDatabase_thenReadCarsByMakeSuccessful() throws Exception {
		mock.perform(get("/cars/read/make/Ford"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	public void givenReadCarsByModel_whenCarsInDatabase_thenReadCarsByModelSuccessful() throws Exception {
		mock.perform(get("/cars/read/model/Camry"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	public void givenReadCarsByType_whenCarsInDatabase_thenReadCarsByTypeSuccessful() throws Exception {
		mock.perform(get("/cars/read/type/Coupe"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	public void givenReadCarsByColor_whenCarsInDatabase_thenReadCarsByColorSuccessful() throws Exception {
		mock.perform(get("/cars/read/color/Red"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
}