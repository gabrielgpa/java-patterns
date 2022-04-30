package com.ezoqc.defijava.services;

import com.ezoqc.defijava.dtos.output.ClientDTO;

public interface ClientService {
	ClientDTO findById(Long id);
}
