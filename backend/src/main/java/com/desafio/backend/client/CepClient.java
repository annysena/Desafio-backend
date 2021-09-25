package com.desafio.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "https://viacep.com.br/ws")
public interface CepClient {

	@GetMapping("/{cep}/json")
	CepResponse consultarCep(@PathVariable("cep") String cep);

}
