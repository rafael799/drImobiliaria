CREATE TABLE honorario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo_transacao VARCHAR(50) NOT NULL,
	mes BIGINT(20) NOT NULL,
	ano BIGINT(20) NOT NULL,
	data_lancamento DATE NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	codigo_tipo_honorario BIGINT(20) NOT NULL,
	codigo_contrato_locacao BIGINT(20),
	FOREIGN KEY (codigo_tipo_honorario) REFERENCES tipo_honorario(codigo),
	FOREIGN KEY (codigo_contrato_locacao) REFERENCES contrato_locacao(codigo)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO honorario (tipo_transacao, mes, ano, data_lancamento, valor, codigo_tipo_honorario,codigo_contrato_locacao) values ('ENTRADA', 1, 2017, '2017-01-01', 1500.00,1,1 );
INSERT INTO honorario (tipo_transacao, mes, ano, data_lancamento, valor, codigo_tipo_honorario,codigo_contrato_locacao) values ('ENTRADA', 2, 2018, '2018-02-02', 500.00,2,2 );
INSERT INTO honorario (tipo_transacao, mes, ano, data_lancamento, valor, codigo_tipo_honorario,codigo_contrato_locacao) values ('ENTRADA', 3, 2012, '2012-03-03', 2500.00,3,3 );
INSERT INTO honorario (tipo_transacao, mes, ano, data_lancamento, valor, codigo_tipo_honorario,codigo_contrato_locacao) values ('SAIDA', 4, 2019, '2019-04-04', 500.00,4,3 );
