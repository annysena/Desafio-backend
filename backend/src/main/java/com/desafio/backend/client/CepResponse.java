package com.desafio.backend.client;

import com.desafio.backend.model.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CepResponse {

	@JsonProperty("cep")
	private String cep;

	@JsonProperty("logradouro")
	private String rua;

	@JsonProperty("bairro")
	private String bairro;

	@JsonProperty("localidade")
	private String cidade;

	@JsonProperty("uf")
	private String estado;

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public Endereco paraEntidade() {
		return new Endereco(this);

	}

}
