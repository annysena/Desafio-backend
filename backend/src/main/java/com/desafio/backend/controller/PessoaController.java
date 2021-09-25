package com.desafio.backend.controller;

import com.desafio.backend.client.CepGateway;
import com.desafio.backend.dto.endereco.EnderecoRequest;
import com.desafio.backend.dto.endereco.EnderecoResponse;
import com.desafio.backend.dto.pessoa.*;
import com.desafio.backend.model.Endereco;
import com.desafio.backend.model.Pessoa;
import com.desafio.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository repository;
	
	@Autowired
	CepGateway gateway;

	@GetMapping("/todas")
	public ResponseEntity<List<PessoaDetalheResponse>> consultarPessoas() {
		List<PessoaDetalheResponse> pessoas = repository.findAll()
				.stream()
				.map(pessoa -> new PessoaDetalheResponse(pessoa))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(pessoas);
	}

	@GetMapping
	public ResponseEntity<PessoaDetalheResponse> consultaParametrizada(@RequestParam(required = false) long id,
																	   @RequestParam(required = false) String cpf,
																	   @RequestParam(required = false) String rg) {
		Optional<Pessoa> pessoa = repository.procurarPessoaPorParametros(id, cpf, rg);

		if(!pessoa.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
		}

		return ResponseEntity.ok().body(new PessoaDetalheResponse(pessoa.get()));
	}

	@PostMapping
	public ResponseEntity<PessoaResponse> cadastrarPessoa(@RequestBody PessoaCadastraRequest request) {
		List<Endereco> enderecos = new ArrayList<>();
		if(request.getEnderecos() != null) {
			enderecos = request.getEnderecos().stream().map(enderecoRequest -> {
				Endereco end = new Endereco(gateway.consultaCep(enderecoRequest.getCep()));
				end.setNumero(enderecoRequest.getNumero());
				return end;
			}).collect(Collectors.toList());
		}
		
		Pessoa pessoa = request.paraEntidade(enderecos);
		pessoa = repository.save(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaResponse(pessoa));
	}

	@PutMapping("/{idPessoa}/enderecos")
	public ResponseEntity<EnderecoResponse> cadastrarEndereco(@PathVariable("idPessoa") long id, @RequestBody EnderecoRequest request) {
		Pessoa pessoa = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

		Endereco endereco = new Endereco(gateway.consultaCep(request.getCep()));
		endereco.setNumero(request.getNumero());

		pessoa.adicionarEndereco(endereco);
		pessoa = repository.save(pessoa);

		Endereco novoEndereco = ultimoEndereco(pessoa.getEnderecos());

		return ResponseEntity.ok().body(new EnderecoResponse(novoEndereco));
	}

	@PutMapping("/{idPessoa}")
	public ResponseEntity<PessoaAtualizaResponse> atualizarPessoa(@PathVariable("idPessoa") long id, @RequestBody PessoaAtualizaRequest request) {
		Pessoa pessoa = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
		pessoa.atualizarDados(request);

		repository.save(pessoa);
		return ResponseEntity.ok().body(new PessoaAtualizaResponse(pessoa));
	}

	@PutMapping("/{idPessoa}/enderecos/{idEndereco}")
	public ResponseEntity<EnderecoResponse> atualizarEndereco(@PathVariable("idPessoa") long id, @PathVariable("idPessoa") long idEnd, @RequestBody EnderecoRequest request) {
		Pessoa pessoa = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
		pessoa.getEnderecos().stream()
				.filter(end -> end.getId() == idEnd)
				.findAny()
				.get()
				.atualizar(gateway.consultaCep(request.getCep()), request.getNumero());

		pessoa = repository.save(pessoa);

		Endereco novoEndereco = ultimoEndereco(pessoa.getEnderecos());

		return ResponseEntity.ok().body(new EnderecoResponse(novoEndereco));
	}

	private Endereco ultimoEndereco(List<Endereco> enderecos) {
		Endereco ultimoEndereco = enderecos.stream().skip(enderecos.size()-1)
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ultimo endereco não encontrado"));
		return ultimoEndereco;
	}
}
