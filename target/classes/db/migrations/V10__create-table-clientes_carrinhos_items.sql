CREATE TABLE carrinhos_items (
	id BIGSERIAL,
	quantidade INTEGER NOT NULL,
	produto_id BIGINT NOT NULL,
	sub_total NUMERIC(10,2) NOT NULL,
	data_e_hora_insercao TIMESTAMP NOT NULL,
	data_e_hora_atualizacao TIMESTAMP,
	carrinho_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (produto_id)
	 REFERENCES produtos (id) ON DELETE CASCADE,
	FOREIGN KEY (carrinho_id)
	 REFERENCES carrinhos (id) ON DELETE CASCADE
);