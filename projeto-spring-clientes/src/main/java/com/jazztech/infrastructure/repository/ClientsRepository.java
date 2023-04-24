package com.jazztech.infrastructure.repository;

import com.jazztech.applicationservice.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientsRepository extends JpaRepository<Client, Long> {
    Client findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    @Transactional
    @Modifying
    @Query("delete from Client c where upper(c.nome) like upper(concat('%', ?1, '%'))")
    int deleteByNomeContainsIgnoreCase(String nome);
}
