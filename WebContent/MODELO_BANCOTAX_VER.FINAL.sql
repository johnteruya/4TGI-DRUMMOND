CREATE TABLE taxas(
codTaxa NUMBER(3) PRIMARY KEY,
tipoTaxa VARCHAR2(30) NOT NULL,
valorTaxa NUMBER(3,2) NOT NULL,
dataVigencia DATE
);


ALTER TABLE taxas MODIFY dataVigencia VARCHAR2(10) NOT NULL;

ALTER TABLE taxas MODIFY dataVigencia DATE;

INSERT INTO taxas VALUES ( 500, 'EMPRESTIMO - JUROS CONTRATO', 3.5, '21-11-2017');
INSERT INTO taxas VALUES ( 600, 'EMPRESTIMO - JUROS MORA', 2.3, '21-11-2017');
INSERT INTO taxas VALUES ( 700, 'EMPRESTIMO - JUROS ATRASO', 1.5, '21-11-2017');

DELETE FROM Taxas

DESC Taxas;

DROP TABLE taxas;

SELECT * FROM taxas;