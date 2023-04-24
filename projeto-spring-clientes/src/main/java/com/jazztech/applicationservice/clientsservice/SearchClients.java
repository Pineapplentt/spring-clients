package com.jazztech.applicationservice.clientsservice;

import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.exception.handler.ClientAlreadyExistsException;
import com.jazztech.exception.handler.ClientNotFoundException;
import com.jazztech.infrastructure.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SearchClients {
    @Autowired
    private final ClientsRepository clientsRepository;

    public SearchClients(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> getClients() {
        List<Client> clients = clientsRepository.findAll();
        if (clients.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(204), "Empty list");
        }
        return clients;
    }

    public Client getClientPorId(Long id) throws ClientNotFoundException {
        return clientsRepository.findById(id)
                .orElseThrow(() ->
                        new ClientNotFoundException("Client not found"));
    }

    public Client getClientPorCpf(String cpf) throws ClientAlreadyExistsException {
        if (clientsRepository.existsByCpf(cpf)) {
            return clientsRepository.findByCpf(cpf);
        }
        throw new ClientAlreadyExistsException("Client not found");
    }
}
