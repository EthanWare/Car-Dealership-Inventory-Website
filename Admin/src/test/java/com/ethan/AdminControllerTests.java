package com.ethan;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringRunner;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mock;

	@Transactional
	@Test
	public void givenCreateCar_whenCarNotInDatabaseAndCarHasNoNullFields_thenCarCreatedSuccessful()
			throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car(1,2000,"Dodge","Cummins","Truck","White"))))
			.andExpect(status().isCreated());
	}

	@Transactional
	@Test
	public void givenCreateCar_whenCarAlreadyInDatabaseAndCarHasNoNullFields_thenCarCreatedFails() throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car(1, 2005, "Honda", "Accord", "Coupe", "Grey"))))
			.andExpect(status().isConflict());
	}
	
	@Transactional
	@Test
	public void givenCreateCar_whenCarNotInDatabaseAndCarisNull_thenCarCreatedFails() throws Exception {
		mock.perform(post("/cars/create").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car())))
			.andExpect(status().isNotAcceptable());
	}
	
	@Transactional
	@Test
	public void givenReadCars_whenCarsInDatabase_thenReadCarsSuccessful() throws Exception {
		mock.perform(get("/cars/read"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Transactional
	@Test
	public void givenReadCars_whenNoCarsInDatabase_thenReadCarsFails() throws Exception {
		//delete all cars before test
		mock.perform(delete("/cars/delete"))
			.andExpect(status().isOk());

		mock.perform(get("/cars/read"))
			.andExpect(status().isNoContent())
			.andExpect(content().string("No cars exist"));
	}
	
	@Transactional
	@Test
	public void givenReadCarsByYear_whenCarsInDatabase_thenReadCarsByYearSuccessful() throws Exception {
		mock.perform(get("/cars/read/year/2005"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}
	
	@Transactional
	@Test
	public void givenReadCarsByMake_whenCarsInDatabase_thenReadCarsByMakeSuccessful() throws Exception {
		mock.perform(get("/cars/read/make/Ford"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Transactional
	@Test
	public void givenReadCarsByModel_whenCarsInDatabase_thenReadCarsByModelSuccessful() throws Exception {
		mock.perform(get("/cars/read/model/Camry"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Transactional
	@Test
	public void givenReadCarsByType_whenCarsInDatabase_thenReadCarsByTypeSuccessful() throws Exception {
		mock.perform(get("/cars/read/type/Coupe"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Transactional
	@Test
	public void givenReadCarsByColor_whenCarsInDatabase_thenReadCarsByColorSuccessful() throws Exception {
		mock.perform(get("/cars/read/color/Red"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").exists());
	}

	@Transactional
	@Test
	public void givenUpdateCar_whenCarAlreadyInDatabaseAndCarHasNoNullFields_thenUpdateCarSuccessful() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car(1, 1969, "Dodge", "Charger", "Coupe", "Orange"))))
			.andExpect(status().isOk());
	}

	@Transactional
	@Test
	public void givenUpdateCar_whenCarDoesNotExistInDatabaseAndCarHasNoNullFields_thenUpdateCarFails() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car(200, 1111, "Dodge", "Caaa", "Coupe", "aitoi448sdfnd"))))
			.andExpect(status().isNotFound());
	}

	@Transactional
	@Test
	public void givenUpdateCar_whenCarAlreadyInDatabaseAndCarHasNullFields_thenUpdateCarFails() throws Exception {
		mock.perform(put("/cars/update").contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(OBJECT_MAPPER.writeValueAsString(new Car(7, 1969, "Dodge", "", "Coupe", "Orange"))))
			.andExpect(status().isNotAcceptable());
	}

	@Transactional
	@Test
	public void givenDeleteCarById_whenCarsInDatabase_thenDeleteCarByIdSuccessful() throws Exception {
		mock.perform(delete("/cars/delete/3"))
			.andExpect(status().isOk());
	}

	@Transactional
	@Test
	public void givenDeleteCarById_whenNoCarsInDatabase_thenDeleteCarByIdFails() throws Exception {
		//delete all cars before test
		mock.perform(delete("/cars/delete"))
			.andExpect(status().isOk());

		mock.perform(delete("/cars/delete/3"))
			.andExpect(status().isNotFound());
	}

	@Transactional
	@Test
	public void givenDeleteAllCars_whenCarsInDatabase_thenDeleteAllCarsSuccessful() throws Exception {
		mock.perform(delete("/cars/delete"))
			.andExpect(status().isOk());
	}
	
	@Transactional
	@Test
	public void givenDeleteAllCars_whenNoCarsInDatabase_thenDeleteAllCarsFails() throws Exception {
		//delete all cars before test
		mock.perform(delete("/cars/delete"))
			.andExpect(status().isOk());

		mock.perform(delete("/cars/delete"))
			.andExpect(status().isNoContent());
	}
}
