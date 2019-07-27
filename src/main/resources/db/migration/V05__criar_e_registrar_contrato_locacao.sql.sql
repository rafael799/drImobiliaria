CREATE TABLE contrato_locacao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_vencimento DATE NOT NULL,
	valor_aluguel DECIMAL(10,2) NOT NULL,
	duracao_meses BIGINT(20) NOT NULL,
	considera_pagamento_honorario BOOLEAN NOT NULL,
	situacao BOOLEAN NOT NULL,
	codigo_imovel BIGINT(20) NOT NULL,
	codigo_locador BIGINT(20) NOT NULL,
	codigo_locatario BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_imovel) REFERENCES imovel(codigo),
	FOREIGN KEY (codigo_locador) REFERENCES locador(codigo),
	FOREIGN KEY (codigo_locatario) REFERENCES locatario(codigo)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contrato_locacao (data_vencimento, valor_aluguel, duracao_meses , considera_pagamento_honorario, situacao, codigo_imovel, codigo_locador, codigo_locatario) values ('2017-06-10', 6500.00, 12, true, true, 1, 1,1);
INSERT INTO contrato_locacao (data_vencimento, valor_aluguel, duracao_meses , considera_pagamento_honorario, situacao, codigo_imovel, codigo_locador, codigo_locatario) values ('2018-03-11', 1500.00, 12, true, true, 2, 2,2);
INSERT INTO contrato_locacao (data_vencimento, valor_aluguel, duracao_meses , considera_pagamento_honorario, situacao, codigo_imovel, codigo_locador, codigo_locatario) values ('2019-05-12', 2000.00, 32, true, true, 3, 2,1);
INSERT INTO contrato_locacao (data_vencimento, valor_aluguel, duracao_meses , considera_pagamento_honorario, situacao, codigo_imovel, codigo_locador, codigo_locatario) values ('2017-01-05', 3500.00, 12, true, true, 2, 3,1);
INSERT INTO contrato_locacao (data_vencimento, valor_aluguel, duracao_meses , considera_pagamento_honorario, situacao, codigo_imovel, codigo_locador, codigo_locatario) values ('2017-01-01', 2500.00, 21, true, true, 3, 2,2);