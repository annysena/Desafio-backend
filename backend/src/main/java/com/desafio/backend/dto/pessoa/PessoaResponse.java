package com.desafio.backend.dto.pessoa;

import com.desafio.backend.model.Pessoa;

public class PessoaResponse {

	private long id;

	private String nome;

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public PessoaResponse(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
	}

}
