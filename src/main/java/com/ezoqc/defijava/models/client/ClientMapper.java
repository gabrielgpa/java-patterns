package com.ezoqc.defijava.models.client;

import org.springframework.stereotype.Component;

import com.ezoqc.defijava.dtos.output.ClientDTO;
import com.ezoqc.defijava.helpers.Mapper;

@Component
public class ClientMapper implements Mapper<Client, ClientDTO> {

	@Override
	public Client toEntity(ClientDTO data) {
		return new Client.ClientBuilder()
				.id(data.getId())
				.firstName(data.getFirstName())
				.lastName(data.getLastName())
				.socialSecurityNumber(data.getSocialSecurityNumber())
				.birthdate(data.getBirthdate())
				.build();
	}

	@Override
	public ClientDTO toDTO(Client data) {
		return new ClientDTO.ClientDTOBuilder()
				.id(data.getId())
				.firstName(data.getFirstName())
				.lastName(data.getLastName())
				.socialSecurityNumber(data.getSocialSecurityNumber())
				.birthdate(data.getBirthdate())
				.age(data.getAge())
				.build();
	}
}
