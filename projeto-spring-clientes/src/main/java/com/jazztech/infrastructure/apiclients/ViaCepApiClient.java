package com.jazztech.infrastructure.apiclients;

import com.jazztech.applicationservice.domain.entity.Adress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws", name = "ViaCepApi")
public interface ViaCepApiClient {
    @GetMapping("/{cep}/json")
    Adress address(@PathVariable String cep);
}
