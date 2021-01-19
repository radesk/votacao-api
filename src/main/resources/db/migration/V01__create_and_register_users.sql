CREATE TABLE usuario(
	id VARCHAR(36) NOT NULL PRIMARY KEY UNIQUE,
	cpf BIGINT(11) NOT NULL UNIQUE,
	vota BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (cpf, id, vota) values (59225316038, "9ed30817-f391-43dc-88ea-5b44bd0796d2", true);
INSERT INTO usuario (cpf, id, vota) values (67203243060, "5d41e95f-480a-410b-a88a-3cdf96b0467d", false);
INSERT INTO usuario (cpf, id) values (16908475069, "f2b90565-6c5e-4348-addf-2ced6880c712");
