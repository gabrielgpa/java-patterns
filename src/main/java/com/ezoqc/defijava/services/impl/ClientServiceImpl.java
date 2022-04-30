package com.ezoqc.defijava.services.impl;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezoqc.defijava.dtos.output.ClientDTO;
import com.ezoqc.defijava.helpers.Validators;
import com.ezoqc.defijava.models.client.Client;
import com.ezoqc.defijava.models.client.ClientMapper;
import com.ezoqc.defijava.repositories.ClientDAO;
import com.ezoqc.defijava.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	Logger logger = Logger.getLogger(ClientServiceImpl.class.getName());

	@Autowired
    private ClientDAO clientDAO;
	
	private ClientMapper clientMapper;
	
	@Autowired
	public ClientServiceImpl(ClientMapper clientMapper) {
		this.clientMapper = clientMapper;
	}
	
	@Override
	public ClientDTO findById(Long id) {
		Optional<Client> clientFound = clientDAO.findById(id);
		
		Validators.isFound(clientFound);
		
		logger.log(Level.INFO, "Fetch client with id: " + clientFound.get().getId());
		
		return clientMapper.toDTO(clientFound.get());
	}
}
