package com.jazztech.applicationservice.clientsservice;

import com.jazztech.applicationservice.domain.entity.Adress;
import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.exception.handler.ClientAlreadyExistsException;
import com.jazztech.exception.handler.ClientNotFoundException;
import com.jazztech.infrastructure.apiclients.ViaCepApiClient;
import com.jazztech.infrastructure.apiclients.dto.ClientDto;
import com.jazztech.infrastructure.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateClients {
    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ViaCepApiClient api;

    public CreateClients(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ClientDto saveClient(ClientDto client) throws ClientAlreadyExistsException {
        if (clientsRepository.existsByCpf(client.getCpf())) {
            throw new ClientAlreadyExistsException("Client already exists");
        }
        Adress adress = api.address(client.getCep());
        client.setAdress(adress);
        clientsRepository.save(new Client(client));
        return client;
    }

    public String deleteAll(String nome) {
        clientsRepository.deleteByNomeContainsIgnoreCase(nome);
        return "deletados";
    }
}
