package com.desafio.backend.dto.pessoa;

import com.desafio.backend.model.Pessoa;

public class PessoaAtualizaResponse {

    private long id;
    private String nome;
    private String cpf;
    private String rg;

    public PessoaAtualizaResponse(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.rg = pessoa.getRg();
    }

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
}
