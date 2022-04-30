package com.ezoqc.defijava.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ezoqc.defijava.configurations.Env;
import com.ezoqc.defijava.models.client.Client;
import com.ezoqc.defijava.repositories.ClientDAO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Env env;
	
	@MockBean
	private ClientDAO clientDAO;
	
	HttpHeaders headers = new HttpHeaders();
	public static final String API_PATH = "/api/v1";
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String BASIC_AUTHORIZATION_FORMAT = "%s:%s";
	public static final String HEADER_BASIC_AUTHORIZATION_FORMAT = "Basic %s";
	public String HEADER_AUTHORIZATION_BASIC_TOKEN = "";
	
	@BeforeEach
	public void setup() {
		String basicAuth = String.format(BASIC_AUTHORIZATION_FORMAT, env.securityUsername, env.securityPassword);
		
		HEADER_AUTHORIZATION_BASIC_TOKEN = Base64.getEncoder().encodeToString(basicAuth.getBytes());
		headers.add(HEADER_AUTHORIZATION, String.format(HEADER_BASIC_AUTHORIZATION_FORMAT, HEADER_AUTHORIZATION_BASIC_TOKEN));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, 11, 5);
		Client client = new Client.ClientBuilder()
				.id(1L)
				.firstName("Gabriel")
				.lastName("Araujo")
				.socialSecurityNumber("234 345 978")
				.birthdate(calendar.getTime())
				.build();
		
		Mockito.when(clientDAO.findById(client.getId()))
			.thenReturn(Optional.of(client));
	}
	
	@Test
	void whenInvalidHeaderAuthorizationThenReturns401() throws Exception {
		String invalidBasicToken = String.format(HEADER_BASIC_AUTHORIZATION_FORMAT, "abcd");
		HttpHeaders invalidHeader = new HttpHeaders();
		invalidHeader.add(HEADER_AUTHORIZATION, invalidBasicToken);
		
		mockMvc.perform(get(API_PATH.concat("/clients/{id}"), 1L)
	        .contentType("application/json")
	        .headers(invalidHeader))
	        .andExpect(status().isUnauthorized());
	}

	@Test
	void whenValidPathVariavbleThenReturns200() throws Exception {
	   mockMvc.perform(get(API_PATH.concat("/clients/{id}"), 1L)
	        .contentType("application/json")
	        .headers(headers))
	        .andExpect(status().isOk());
	}
	
	@Test
	void whenInvalidPathVariableThenReturns404() throws Exception {
		mockMvc.perform(get(API_PATH.concat("/clients/{id}"), 321L)
	        .contentType("application/json")
	        .headers(headers))
	        .andExpect(status().isNotFound());
	}
}
