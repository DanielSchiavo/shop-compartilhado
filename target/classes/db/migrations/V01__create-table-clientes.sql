CREATE TABLE clientes (
	id BIGSERIAL,
	cpf CHAR(11) NOT NULL UNIQUE,
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL,
	data_criacao_conta DATE NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	senha VARCHAR(100) NOT NULL,
	celular CHAR(11) UNIQUE,
	foto_perfil VARCHAR(40) NOT NULL,
	PRIMARY KEY(id)
);