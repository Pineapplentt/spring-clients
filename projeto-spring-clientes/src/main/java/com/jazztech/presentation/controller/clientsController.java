package com.jazztech.presentation.controller;

import com.jazztech.applicationservice.clientsservice.CreateClients;
import com.jazztech.applicationservice.clientsservice.SearchClients;
import com.jazztech.applicationservice.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Client> getClientPorId(@RequestParam Long id) {
        Client client = search.getClientPorId(id);
        return ResponseEntity.status(200).body(client);
    }

    @GetMapping("/cpf")
    public ResponseEntity<Client> getClientporCpf(@RequestParam String cpf) {
        Client client = search.getClientPorCpf(cpf);
        return ResponseEntity.status(200).body(client);
    }

    @PostMapping
    public ResponseEntity<String> postClient(@RequestBody Client client) {
        create.saveClient(client);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/remove-all")
    public ResponseEntity<String> deleteAll(@RequestParam String nome) {
        create.deleteAll(nome);
        return ResponseEntity.status(200).build();
    }
}
