CREATE TABLE imovel (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	situacao BOOLEAN NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 1', true, 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 'Uberlândia', 'MG');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 2', true, 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 'Ribeirão Preto', 'SP');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 3', true, 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 'Goiânia', 'GO');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 4', true, 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 5', true, 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 'Natal', 'RN');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 6', true, 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 'Porto Alegre', 'RS');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 7', true, 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 'Rio de Janeiro', 'RJ');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 8', true, 'Rua da Manga', '433', null, 'Centro', '31.400-121', 'Belo Horizonte', 'MG');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 9', true, 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG');
INSERT INTO locatario (descricao, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Casa 10', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', 'Manaus', 'AM');