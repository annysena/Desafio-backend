# Desafio back-end realizando cadastro de Pessoas e Endereços

Aplicação feita em Spring Boot 2.3.4 para realizar o cadastro de pessoas e em seguida as relacionando com seu endereço consultado através do CEP, utilizando a API externa do endereço https://viacep.com.br/ws/01001000/json/ para realizar as consultas.

A aplicação consiste em:
- Cadastro de pessoas com endereço através do POST;
- Cadastro de pessoas sem endereço através do POST;
- Consulta de pessoas através do GET;
- Consulta de CEP através do GET;

O projeto deverá estar com um ambiente de testes configurado acessando o banco de dados MySQL com senha padrão (root, root), deverá usar Maven como gerenciador de dependência, e Java 11 como linguagem.

A entidade Pessoa possui nome, CPF, RG e endereço. Já a entidade Endreço possui CEP, rua, número, bairro, cidade e estado.

   No Postiman (ou outro ambiente de testes)
---
    Inserção de pessoas e seu respectivo endereço
    POST -> http://localhost:8080/pessoas
    
    {
    "nome": "",
    "cpf": "",
    "rg": "", 
    "enderecos":[
        {
        "cep": "",
        "numero": ""
        },
        {
        "cep": "",
        "numero": ""
        }
     ] 
  }
  
---
    Consultar pessoas
    GET -> http://localhost:8080/pessoas/todas
   
---
    Consultar CEP
    GET -> http://localhost:8080/cep/02757110
---

OBS: Lembrando sempre que as informações que inserimos pode ser de sua preferência. 
