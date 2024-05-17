CREATE TABLE clientes_roles (
    id BIGINT NOT NULL,
    data_e_hora_atribuicao TIMESTAMP NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) 
     REFERENCES clientes (id) 
      ON DELETE CASCADE
);
