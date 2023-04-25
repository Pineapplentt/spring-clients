package com.jazztech.applicationservice.clientsservice;

import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.exception.handler.ClientAlreadyExistsException;
import com.jazztech.exception.handler.ClientNotFoundException;
import com.jazztech.infrastructure.repository.ClientsRepository;
import com.jazztech.presentation.controller.clientsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(SearchClients.class);

    public SearchClients(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> getClients() {
        List<Client> clients = clientsRepository.findAll();
        if (clients.isEmpty()) {
            logger.info("O método getClients() foi utilizado e a lista retornada está vazia");
            throw new ResponseStatusException(HttpStatusCode.valueOf(204), "Lista vazia");
        }
        return clients;
    }

    public Client getClientPorId(Long id) throws ClientNotFoundException {
        return clientsRepository.findById(id)
                .orElseThrow(() ->
                        new ClientNotFoundException(String.format("Cliente com ID %d não encontrado", id)));
    }

    public Client getClientPorCpf(String cpf) throws ClientAlreadyExistsException {
        if (clientsRepository.existsByCpf(cpf)) {
            return clientsRepository.findByCpf(cpf);
        }
        logger.info("getClientPorCpf(), cliente não encontrado");
        throw new ClientAlreadyExistsException(String.format("Cliente com o cpf %s não encontrado", cpf));
    }
}
