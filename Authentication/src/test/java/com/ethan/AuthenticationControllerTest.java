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

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mock;

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
}