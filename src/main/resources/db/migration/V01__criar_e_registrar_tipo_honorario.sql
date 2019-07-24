CREATE TABLE tipo_honorario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipo_honorario (descricao) values ('Agua');
INSERT INTO tipo_honorario (descricao) values ('Luz');
INSERT INTO tipo_honorario (descricao) values ('Gás');
INSERT INTO tipo_honorario (descricao) values ('Aluguel');
INSERT INTO tipo_honorario (descricao) values ('IPTU');
INSERT INTO tipo_honorario (descricao) values ('Mantenção');