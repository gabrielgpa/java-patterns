package com.ezoqc.defijava.configurations.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityConfigurerAdapterTest {

	public static final String ORIGIN = "Origin";
	public static final String API_PATH = "/api/v1";
	public static final String HTTP_FAIL_URL = "https://fail.com";
	public static final String HTTP_SUCCESS_URL = "http://localhost:8080";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldAcceptByCors() throws Exception {
		mockMvc.perform(options(API_PATH.concat("/clients/1")).contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
				.header(ORIGIN, HTTP_SUCCESS_URL)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void shouldDenyByCors() throws Exception {
		mockMvc.perform(options(API_PATH.concat("/clients/1")).contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name()).header(ORIGIN, HTTP_FAIL_URL))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}
}
