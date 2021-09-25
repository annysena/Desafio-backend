package com.desafio.backend.model;

import com.desafio.backend.dto.pessoa.PessoaAtualizaRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private String cpf;

	private String rg;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Endereco> enderecos = new ArrayList<>();


	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void adicionarEndereco(Endereco endereco) {
		endereco.setPessoa(this);
		this.enderecos.add(endereco);
	}

	public Pessoa atualizarDados(PessoaAtualizaRequest request) {
		this.nome = request.getNome();
		this.cpf = request.getCpf();
		this.rg = request.getRg();
		return this;
	}

	public Pessoa(String nome, String cpf, String rg, List<Endereco> novosEnderecos) {
		novosEnderecos.forEach(end -> end.setPessoa(this));
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.enderecos.addAll(novosEnderecos);
	}

	public Pessoa(String nome, String cpf, String rg) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
	}

	public Pessoa() {
	}
}
