package com.ezoqc.defijava.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezoqc.defijava.dtos.output.ClientDTO;
import com.ezoqc.defijava.exceptions.NotFoundException;
import com.ezoqc.defijava.services.ClientService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController extends AbstractController {

	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable(value = "id") @Valid Long id) throws NotFoundException {
		return ok(this.clientService.findById(id));
	}
}
