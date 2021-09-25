package com.desafio.backend.controller;

import com.desafio.backend.client.CepGateway;
import com.desafio.backend.client.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	 
	@Autowired
	CepGateway gateway;

	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepResponse> consultarCep(@PathVariable("cep") String cep) {
		return ResponseEntity.ok().body(gateway.consultaCep(cep));
	}


}
