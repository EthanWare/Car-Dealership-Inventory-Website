package com.ethan;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AdminControllerTests {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mock;

	@Test
	@Order(1)
	public void givenCreateCar_whenCarNotInDatabaseAndCarHasNoNullFields_thenCarCreatedSuccessful()
			throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car(1,2000,"Dodge","Cummins","Truck","White"))))
		.andExpect(status().isCreated());
	}

	
	@Test
	@Order(2)
	public void givenCreateCar_whenCarAlreadyInDatabaseAndCarHasNoNullFields_thenCarCreatedFails() throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car(1, 2005, "Honda", "Accord", "Coupe", "Grey"))))
		.andExpect(status().isConflict());
	}
	
	@Test
	@Order(3)
	public void givenCreateCar_whenCarNotInDatabaseAndCarisNull_thenCarCreatedFails() throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car())))
		.andExpect(status().isNotAcceptable());
	}
	
	@Test
	@Order(4)
	public void givenReadCars_whenCarsInDatabase_thenReadCarsSuccessful() throws Exception {
		mock.perform(get("/cars/read").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	/*
	@Test
	@Order(5)
	public void givenReadFilteredCar_whenCarsInDatabase_thenReadFilteredCarsSuccessful() throws Exception {
		mock.perform(get("/read/make/Ford"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	



	
	@Test
	@Order(10)
	public void givenReadCars_whenNoCarsInDatabase_thenReadCarsFails() throws Exception {
		mock.perform(get("/cars/read").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isNoContent())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").doesNotExist());
	}
	*/







	/*
	@Test
	public void givenUser_whenAuthenticate_thenReturn0() throws Exception {
		mock.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new User(0,"Ethan","pass",0))))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().string("0"));
	}

	@Test
	public void givenAdmin_whenAuthenticate_thenReturn1() throws Exception {
		mock.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new User(0,"admin","admin",1))))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().string("1"));
	}

	@Test
	public void givenNonUser_whenAuthenticate_thenReturnNegative1() throws Exception {
		mock.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new User(0,"Not a User","wrong",1))))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().string("-1"));
	}
	*/
}
