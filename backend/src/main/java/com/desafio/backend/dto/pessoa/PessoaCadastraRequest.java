package com.desafio.backend.dto.pessoa;


import com.desafio.backend.dto.endereco.EnderecoRequest;
import com.desafio.backend.model.Endereco;
import com.desafio.backend.model.Pessoa;

import java.util.List;
import java.util.Set;


public class PessoaCadastraRequest {

	private String nome;

	private String cpf;

	private String rg;

	private List<EnderecoRequest> enderecos;

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public List<EnderecoRequest> getEnderecos() {
		return enderecos;
	}
	
	public Pessoa paraEntidade(List<Endereco> enderecosRequest) {
		if(enderecosRequest != null) {
			return new Pessoa(nome, cpf, rg, enderecosRequest);
		}
		return new Pessoa(nome, cpf, rg);
	}

}
