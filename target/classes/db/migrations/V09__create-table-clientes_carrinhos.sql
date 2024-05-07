CREATE TABLE clientes_carrinhos (
	cliente_id BIGINT,
	valor_total NUMERIC(10,2),
	data_e_hora_atualizacao TIMESTAMP,
	PRIMARY KEY(cliente_id),
	FOREIGN KEY (cliente_id)
	 REFERENCES clientes (id) ON DELETE CASCADE
);

