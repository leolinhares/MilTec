CREATE DATABASE MilTec;

CREATE TABLE fornecedor(
	id serial PRIMARY KEY,
	nome VARCHAR (50) NOT NULL,
	cnpj INTEGER NOT NULL,
	endereco VARCHAR(100)
);


CREATE TABLE Produto(
	id serial PRIMARY KEY,
	nome TEXT NOT NULL,
	marca TEXT NOT NULL,
	data_cadastro DATE,
	valor_unitario DECIMAL NOT NULL,
	quantidade INTEGER NOT NULL,
	fornecedor_id INTEGER REFERENCES fornecedor(id)
);

SELECT *
FROM produto;


SELECT fornecedor.nome, fornecedor.cnpj, sum(valor_unitario*quantidade)
FROM produto, fornecedor
WHERE produto.fornecedor_id = fornecedor.id
GROUP BY fornecedor.id;

SELECT nome, valor_unitario
FROM produto
WHERE valor_unitario = (SELECT max(valor_unitario) FROM produto);


SELECT fornecedor.id, fornecedor.nome
FROM fornecedor
WHERE fornecedor.id not in (select fornecedor_id from produto);
