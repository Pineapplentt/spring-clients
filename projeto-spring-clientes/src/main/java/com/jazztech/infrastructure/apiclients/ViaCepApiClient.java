package com.jazztech.infrastructure.apiclients;

import com.jazztech.infrastructure.apiclients.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br", name = "ViaCepApi")
public interface ViaCepApiClient {
    @GetMapping("/{cep}/json")
    ClientDto address(@PathVariable String cep);
}
