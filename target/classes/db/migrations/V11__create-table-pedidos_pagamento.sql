CREATE TABLE pedidos_pagamento (
	id BIGSERIAL,
	metodo_pagamento VARCHAR(50) NOT NULL,
	status_pagamento VARCHAR(50) NOT NULL,
	data_pagamento TIMESTAMP NOT NULL,
	nome_banco VARCHAR(255),
	numero_cartao CHAR(16),
	nome_no_cartao VARCHAR(255),
	validade_cartao CHAR(5),
	numero_de_parcelas VARCHAR(2),
	tipo_cartao VARCHAR(15) CHECK (tipo_cartao IN ('CREDITO','DEBITO')),
	PRIMARY KEY (id)
);	