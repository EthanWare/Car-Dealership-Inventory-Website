package com.ethan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	
	@Test
	@Order(5)
	public void givenReadCarsByYear_whenCarsInDatabase_thenReadCarsByYearSuccessful() throws Exception {
		mock.perform(get("/cars/read/year/2005"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	
	@Test
	@Order(6)
	public void givenReadCarsByMake_whenCarsInDatabase_thenReadCarsByMakeSuccessful() throws Exception {
		mock.perform(get("/cars/read/make/Ford"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	@Order(7)
	public void givenReadCarsByModel_whenCarsInDatabase_thenReadCarsByModelSuccessful() throws Exception {
		mock.perform(get("/cars/read/model/Camry"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	@Order(8)
	public void givenReadCarsByType_whenCarsInDatabase_thenReadCarsByTypeSuccessful() throws Exception {
		mock.perform(get("/cars/read/type/Coupe"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	@Order(9)
	public void givenReadCarsByColor_whenCarsInDatabase_thenReadCarsByColorSuccessful() throws Exception {
		mock.perform(get("/cars/read/color/Red"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Test
	@Order(10)
	public void givenUpdateCar_whenCarAlreadyInDatabaseAndCarHasNoNullFields_thenUpdateCarSuccessful() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car(1, 1969, "Dodge", "Charger", "Coupe", "Orange"))))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(11)
	public void givenUpdateCar_whenCarDoesNotExistInDatabaseAndCarHasNoNullFields_thenUpdateCarFails() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car(200, 1969, "Dodge", "Charger", "Coupe", "Orange"))))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(12)
	public void givenUpdateCar_whenCarAlreadyInDatabaseAndCarHasNullFields_thenUpdateCarFails() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(OBJECT_MAPPER.writeValueAsString(new Car(7, 1969, "Dodge", "", "Coupe", "Orange"))))
		.andExpect(status().isNotAcceptable());
	}

	@Test
	@Order(13)
	public void givenDeleteCarById_whenCarsInDatabase_thenDeleteCarByIdSuccessful() throws Exception {
		mock.perform(delete("/cars/delete/3"))
		.andExpect(status().isOk());
	}

	@Test
	@Order(14)
	public void givenDeleteCarById_whenNoCarsInDatabase_thenDeleteCarByIdFails() throws Exception {
		mock.perform(delete("/cars/delete/3"))
		.andExpect(status().isNotFound());
	}

	
	@Test
	@Order(15)
	public void givenDeleteAllCars_whenCarsInDatabase_thenDeleteAllCarsSuccessful() throws Exception {
		mock.perform(delete("/cars/delete"))
		.andExpect(status().isOk());
	}
	/*
	@Test
	@Order(16)
	public void givenDeleteAllCars_whenNoCarsInDatabase_thenDeleteAllCarsFails() throws Exception {
		mock.perform(delete("/cars/delete"))
		.andExpect(status().isNoContent());
	}

	@Test
	@Order(17)
	public void givenReadCars_whenNoCarsInDatabase_thenReadCarsFails() throws Exception {
		mock.perform(get("/cars/read").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isNoContent())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").doesNotExist());
	}*/
}
