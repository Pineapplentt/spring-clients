package com.jazztech.presentation.controller;

import com.jazztech.applicationservice.clientsservice.CreateClients;
import com.jazztech.applicationservice.clientsservice.SearchClients;
import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.exception.handler.ClientAlreadyExistsException;
import com.jazztech.exception.handler.ClientNotFoundException;
import com.jazztech.exception.handler.MyExceptionHandler;
import com.jazztech.infrastructure.apiclients.dto.ClientDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class clientsController {
    @Autowired
    private CreateClients create;

    @Autowired
    private SearchClients search;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = search.getClients();
        return ResponseEntity.status(200).body(clients);
    }

    @GetMapping("/id")
    public ResponseEntity<Client> getClientPorId(@RequestParam Long id) throws ClientNotFoundException{
        Client client = search.getClientPorId(id);
        return ResponseEntity.status(200).body(client);
    }

    @GetMapping("/cpf")
    public ResponseEntity<Client> getClientporCpf(@RequestParam String cpf) throws ClientAlreadyExistsException {
        Client client = search.getClientPorCpf(cpf);
        return ResponseEntity.status(200).body(client);
    }

    @PostMapping
    public ResponseEntity<ClientDto> postClient(@RequestBody @Valid ClientDto client) throws ClientAlreadyExistsException {
        return ResponseEntity.status(201).body(create.saveClient(client));
    }

    @DeleteMapping("/remove-all")
    public ResponseEntity<String> deleteAll(@RequestParam String nome) {
        create.deleteAll(nome);
        return ResponseEntity.status(200).build();
    }
}
