﻿# 4º TGI-DRUMMOND
Trabalho de Integração de Grupo - Simulação de um Sistema Bancário.

## Banco de Dados - Oracle SQL
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
