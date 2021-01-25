CREATE TABLE pauta(
	id VARCHAR(36) NOT NULL PRIMARY KEY UNIQUE,
    nome VARCHAR(50) NOT NULL,
	descricao VARCHAR(50),
    encerramento TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE votos(
	voto BOOLEAN NOT NULL,
    id_usuario VARCHAR(36) NOT NULL,
    id_pauta VARCHAR(36) NOT NULL,
    PRIMARY KEY (id_usuario, id_pauta),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_pauta) REFERENCES pauta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO pauta (id, nome, descricao, encerramento) values ("dc3d45bf-186d-425b-a6c7-580a2c761eb1", "Teste", "Pauta de teste para iniciar o projeto", '2021-02-01 00:00:01');
INSERT INTO votos (voto, id_usuario, id_pauta) values (1, "5d41e95f-480a-410b-a88a-3cdf96b0467d", "dc3d45bf-186d-425b-a6c7-580a2c761eb1");
INSERT INTO votos (voto, id_usuario, id_pauta) values (1, "9ed30817-f391-43dc-88ea-5b44bd0796d2", "dc3d45bf-186d-425b-a6c7-580a2c761eb1");
INSERT INTO votos (voto, id_usuario, id_pauta) values (0, "f2b90565-6c5e-4348-addf-2ced6880c712", "dc3d45bf-186d-425b-a6c7-580a2c761eb1");
