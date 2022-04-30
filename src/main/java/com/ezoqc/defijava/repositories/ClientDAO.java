package com.ezoqc.defijava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ezoqc.defijava.models.client.Client;

@Repository
public interface ClientDAO extends CrudRepository<Client, Long> {
}
