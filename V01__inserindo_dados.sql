INSERT INTO categoria(nome_categoria, descricao) VALUES('Mouses', 'mouses, mouses coloridos, mouses com 5 botões, mouses');
INSERT INTO categoria(nome_categoria, descricao) VALUES('Teclados', 'teclas, tec-tec, teclas que brilham, teclas');

INSERT INTO produto(nome, descricao, data_cadastro, qtd_estoque, valor_unitario, id_categoria ) VALUES('Teclados RTX2000', 'teclas que brilham e falam, LGBT lights','2022-10-10', 3, 120, 2);
INSERT INTO produto(nome, descricao, data_cadastro, qtd_estoque, valor_unitario, id_categoria ) VALUES('Mouse Stuart rato', 'mouse mouse, mouse com 5 teclas, mouse que mexe e mira sozinho, mouse','2022-10-26', 8, 27.5, 1);

INSERT INTO cliente(nome_completo, cpf, data_nascimento, email, telefone) VALUES ('Pedro Braga', '13056167789','1996-10-26','pedrin@rockin.com','22654788');

INSERT INTO endereco(cep, rua, numero, complemento, bairro, cidade, uf, id_cliente) VALUES ('25645002', 'Rua alcides penha',  202,'Rua de trás','Centro', 'Frio de Janeiro', 'AM',1);