package com.jazztech.applicationservice.domain.entity;

import com.jazztech.infrastructure.apiclients.dto.ClientDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser nulo nem vazio")
    private String nome;

    @NotBlank(message = "O cpf não pode ser nulo nem vazio")
    @Length(min = 11, max = 11, message = "O cpf não pode ter menos, nem mais de 11 caracteres")
//    @CPF
    private String cpf;

    @PastOrPresent(message = "A data deve ser passada ou presente.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O cep não pode ser nulo nem vazio")
    @Min(value = 8, message = "O cep não pode ter menos de 8 dígitos")
    private String cep;

    @NotNull(message = "Verifique se o campo 'numeroResidencia' está vazio")
    private int numeroResidencia;

    @NotBlank(message = "O complemento não pode ser nulo nem vazio")
    private String complemento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Adress adress;

    public Client(ClientDto client) {
        this.id = client.getId();
        this.nome = client.getNome();
        this.cpf = client.getCpf();
        this.dataNascimento = client.getDataNascimento();
        this.cep = client.getCep();
        this.numeroResidencia = client.getNumeroResidencia();
        this.complemento = client.getComplemento();
        this.adress = client.getAdress();
    }

    public Client() {}

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
