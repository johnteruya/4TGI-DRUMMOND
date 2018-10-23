SistemaCadastro_VER.5.2select * from Cadastro;

drop table Cadastro;

drop sequence codCliente;

CREATE TABLE Cadastro(
	codCliente number(5) PRIMARY KEY,
	nomeCliente varchar2 (50) NOT NULL,   
	dataNasc DATE NOT NULL,
	sexo varchar2(9) NOT NULL,
	rg varchar2(9) NOT NULL,
	cpf  varchar2(11) NOT NULL,
	endereco varchar2(30) NOT NULL,
	numero number(5) NOT NULL,
	complemento varchar2(30), 
	bairro varchar(30) NOT NULL,
	cep number (8)NOT NULL,
	cidade varchar2 (30) NOT NULL,
	estado varchar2 (30) NOT NULL,
	telefone number(10) NOT NULL,
	celular number(11),
	email varchar2(30),
	empresa varchar2(30) NOT NULL,
	profissao varchar2 (30) NOT NULL,
	renda number(8) NOT NULL
);

CREATE SEQUENCE codCliente
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;
