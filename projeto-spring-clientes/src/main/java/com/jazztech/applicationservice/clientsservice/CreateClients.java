package com.jazztech.applicationservice.clientsservice;

import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.infrastructure.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateClients {
    @Autowired
    private final ClientsRepository clientsRepository;

    public CreateClients(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Client saveClient(Client client) {
        if (clientsRepository.existsByCpf(client.getCpf())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Client already exists");
        }
        clientsRepository.save(client);
        return client;
    }

    public String deleteAll(String nome) {
        clientsRepository.deleteByNomeContainsIgnoreCase(nome);
        return "deletados";
    }
}
