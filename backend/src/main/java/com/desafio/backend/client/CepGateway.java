package com.desafio.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CepGateway {

	@Autowired
	private CepClient client;
	
	public CepResponse consultaCep(String cepRequest) {
		try {
			CepResponse cep = client.consultarCep(cepRequest);
			return cep;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro inesperado, confira se o CEP está correto");
		}
	}
}
