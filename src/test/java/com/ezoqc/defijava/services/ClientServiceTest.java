package com.ezoqc.defijava.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.ezoqc.defijava.dtos.output.ClientDTO;
import com.ezoqc.defijava.exceptions.NotFoundException;
import com.ezoqc.defijava.models.client.Client;
import com.ezoqc.defijava.models.client.ClientMapper;
import com.ezoqc.defijava.repositories.ClientDAO;
import com.ezoqc.defijava.services.impl.ClientServiceImpl;

@SpringBootTest
public class ClientServiceTest {
	
	static class ClientServiceConfiguration {
		@Bean
		public ClientServiceImpl clientServiceImpl() {
			return new ClientServiceImpl(new ClientMapper());
		}
	}
	
	@Autowired
	private ClientServiceImpl clientService;

	@MockBean
	private ClientDAO clientDAO;
	
	@BeforeEach
	public void setup() {
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
	void testThatServiceReturnsValue() {
		ClientDTO cli = clientService.findById(1L);
		assertNotNull(cli);
	}
	
	@Test
	void testThatServiceNotFound() {
		assertThatThrownBy(() -> {
			clientService.findById(123L);
		}).isInstanceOf(NotFoundException.class)
		  .hasMessageContaining("La ressource demandée n’a pas été trouvé.");
	}

	@Test
	void testAge() {
		Client c = new Client();
		Calendar car = Calendar.getInstance();
		car.set(1988, 6, 15);
		c.setBirthdate(new Date(car.toInstant().toEpochMilli()));
		assertEquals(33, Math.floor(c.getAge()));
	}

}
