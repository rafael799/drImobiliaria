CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@drimoveis.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'Maria Silva', 'maria@drimoveis.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_LOCADOR');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_LOCADOR');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_REMOVER_LOCADOR');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_EDITAR_LOCADOR');

INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_CADASTRAR_LOCATARIO');
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_PESQUISAR_LOCATARIO');
INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_REMOVER_LOCATARIO');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_EDITAR_LOCATARIO');

INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_CADASTRAR_IMOVEL');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_PESQUISAR_IMOVEL');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_REMOVER_IMOVEL');
INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_EDITAR_IMOVEL');

INSERT INTO permissao (codigo, descricao) values (13, 'ROLE_CADASTRAR_TIPO_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (14, 'ROLE_PESQUISAR_TIPO_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (15, 'ROLE_REMOVER_TIPO_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (16, 'ROLE_EDITAR_TIPO_HONORARIO');

INSERT INTO permissao (codigo, descricao) values (17, 'ROLE_CADASTRAR_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (18, 'ROLE_PESQUISAR_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (19, 'ROLE_REMOVER_HONORARIO');
INSERT INTO permissao (codigo, descricao) values (20, 'ROLE_EDITAR_HONORARIO');

INSERT INTO permissao (codigo, descricao) values (21, 'ROLE_CADASTRAR_CONTRATO_LOCACAO');
INSERT INTO permissao (codigo, descricao) values (22, 'ROLE_PESQUISAR_CONTRATO_LOCACAO');
INSERT INTO permissao (codigo, descricao) values (23, 'ROLE_REMOVER_CONTRATO_LOCACAO');
INSERT INTO permissao (codigo, descricao) values (24, 'ROLE_EDITAR_CONTRATO_LOCACAO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 14);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 24);

-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 14);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 16);