CREATE TABLE locatario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(50) NOT NULL,
	rg VARCHAR(50),
	email VARCHAR(50),
	telefone VARCHAR(50),
	situacao BOOLEAN NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('João Silva', '09177069609','1592539309', 'joao.silva@gmail.com', '32 988603810', true, 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 'Uberlândia', 'MG');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Maria Rita', '12444495502','1523235554', 'maria@gmail.com', '32 95565505', true, 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 'Ribeirão Preto', 'SP');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Pedro Santos','1111221212','1122261121', 'pedro.silva@gmail.com', '32 988223355', true, 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 'Goiânia', 'GO');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Ricardo Pereira', '02366658540','2545636302', 'ricador@gmail.com', '32 9888556560', true, 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Josué Mariano', '25488863014','2532156988', 'josue@gmail.com', '32 988603810', true, 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 'Natal', 'RN');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Pedro Barbosa', '22233321256','1151515111', 'pedro@gmail.com', '32 988603810', true, 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 'Porto Alegre', 'RS');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Henrique Medeiros', '222321456','1230015405', 'henrique@gmail.com', '32 966553265', true, 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 'Rio de Janeiro', 'RJ');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Carlos Santana', '55523212055','1112454545', 'carlos.silva@gmail.com', '32 988603810', true, 'Rua da Manga', '433', null, 'Centro', '31.400-121', 'Belo Horizonte', 'MG');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Leonardo Oliveira', '22236215501','1592539309', 'leonardosilva@gmail.com', '32 988603810', true, 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG');
INSERT INTO locatario (nome, cpf, rg, email, telefone, situacao, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Isabela Martins', '222336450054','1112233214', 'isablea.silva@gmail.com', '32 988603810', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', 'Manaus', 'AM');