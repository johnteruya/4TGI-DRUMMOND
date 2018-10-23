# 4º TGI-DRUMMOND
Trabalho de Integração de Grupo - Simulação de um Sistema Bancário.

## Banco de Dados - Oracle SQL
### Banco referente ao Cadastro:

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

#### Comandos de Interações Banco:

select * from Cadastro;

drop table Cadastro;

drop sequence codCliente;

CREATE SEQUENCE codCliente
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

### Banco referente a Taxas:

CREATE TABLE taxas(
codTaxa NUMBER(3) PRIMARY KEY,
tipoTaxa VARCHAR2(30) NOT NULL,
valorTaxa NUMBER(3,2) NOT NULL,
dataVigencia DATE
);

#### Comandos de Interações Banco:

INSERT INTO taxas VALUES ( 500, 'EMPRESTIMO - JUROS CONTRATO', 3.5, '21-11-2017');
INSERT INTO taxas VALUES ( 600, 'EMPRESTIMO - JUROS MORA', 2.3, '21-11-2017');
INSERT INTO taxas VALUES ( 700, 'EMPRESTIMO - JUROS ATRASO', 1.5, '21-11-2017');

ALTER TABLE taxas MODIFY dataVigencia VARCHAR2(10) NOT NULL;
ALTER TABLE taxas MODIFY dataVigencia DATE;

DELETE FROM Taxas

DESC Taxas;

DROP TABLE taxas;

SELECT * FROM taxas;
