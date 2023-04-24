package com.jazztech.infrastructure.apiclients.dto;

import com.jazztech.applicationservice.domain.entity.Adress;
import com.jazztech.applicationservice.domain.entity.Client;

import java.time.LocalDate;

public class ClientDto {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String cep;
    private int numeroResidencia;
    private String complemento;
    private Adress adress;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.nome = client.getNome();
        this.cpf = client.getCpf();
        this.dataNascimento = client.getDataNascimento();
        this.cep = client.getCep();
        this.numeroResidencia = client.getNumeroResidencia();
        this.complemento = client.getComplemento();
        this.adress = client.getAdress();
    }

    public ClientDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
