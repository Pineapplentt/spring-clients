package com.jazztech.applicationservice.clientsservice;

import com.jazztech.applicationservice.domain.entity.Adress;
import com.jazztech.applicationservice.domain.entity.Client;
import com.jazztech.exception.handler.CepNotFoundException;
import com.jazztech.exception.handler.ClientAlreadyExistsException;
import com.jazztech.exception.handler.InvalidCepException;
import com.jazztech.infrastructure.apiclients.ViaCepApiClient;
import com.jazztech.infrastructure.apiclients.dto.ClientDto;
import com.jazztech.infrastructure.repository.ClientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClients {
    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ViaCepApiClient api;

    Logger logger = LoggerFactory.getLogger(CreateClients.class);

    public CreateClients(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ClientDto saveClient(ClientDto client)
            throws ClientAlreadyExistsException, CepNotFoundException, InvalidCepException {
        if (!client.getCep().matches("^\\d{8}|\\d{5}-\\d{3}$")) {
            logger.info("saveclient() - CEP inválido");
            throw new InvalidCepException(String.format("O cep %s é inválido", client.getCep()));
        }
        if (clientsRepository.existsByCpf(client.getCpf())) {
            logger.info("saveClient(), cliente já existe");
            throw new ClientAlreadyExistsException(String.format("Cliente com o cpf %s já existe", client.getCpf()));
        }
        client.setAdress(validateCep(client.getCep()));
        clientsRepository.save(new Client(client));
        logger.info("saveClient(), cliente salvo na base de dados");
        return client;
    }

    public Adress validateCep(String cep) throws CepNotFoundException {
        Adress adress = api.address(cep);
        if (adress.getCep() == null) {
            throw new CepNotFoundException(
                    String.format("O cep %s não foi encontrado, verifique a ortografia e tente novamente", cep));
        }
        return adress;
    }

    public String deleteAll(String nome) {
        clientsRepository.deleteByNomeContainsIgnoreCase(nome);
        return "deletados";
    }
}
