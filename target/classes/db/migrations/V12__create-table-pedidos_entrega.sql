CREATE TABLE pedidos_entrega (
	id BIGSERIAL,
	tipo_entrega VARCHAR(50) NOT NULL,
	cep VARCHAR(8),
	rua VARCHAR(200),
	numero VARCHAR(20),
	complemento VARCHAR(255),
	bairro VARCHAR(100),
	cidade VARCHAR(100),
	estado CHAR(2),
	PRIMARY KEY(id)
);