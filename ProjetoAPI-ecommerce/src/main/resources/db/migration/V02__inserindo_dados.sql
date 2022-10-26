-- Inserindo dados na tabela Categoria
INSERT INTO categoria(nome_categoria, descricao)
    VALUES  ('Mouses', 'mouses, mouses coloridos, mouses com 5 botões, mouses'),
            ('Teclados', 'teclas, tec-tec, teclas que brilham, teclas'),
            ('Headset Gamer', 'Headset de vários modelos e marcas (específicos para jogos)'),
            ('Webcam', 'Webcams de alta resolução'),
            ('Gabinetes', 'Gabinetes diversos');

-- Inserindo dados na tabela Produto
INSERT INTO produto(nome, descricao, data_cadastro, qtd_estoque, valor_unitario, id_categoria )
    VALUES  ('Teclados RTX2000', 'teclas que brilham e falam, LGBT lights','2022-10-10', 3, 120, 2),
            ('Mouse Stuart rato', 'mouse mouse, mouse com 5 teclas, mouse que mexe e mira sozinho, mouse','2022-10-26', 8, 27.5, 1),
            ('HyperX Cloud Revolver S', 'Headset Gamer HyperX Cloud Revolver S 7.1 Dolby Digital', '2018-10-05', 7, 833.20, 3),
            ('Webcam Logitech c922', 'Webcam Full HD Logitech C922 Pro Stream com Microfone Embutido, USB, 1080p e Tripé Incluso', '2019-12-18', 15, 788.15, 4),
            ('Gabinete Sharkoon TG5', 'Gabinete Sharkoon TG5 Blue Vidro Temperado 4mm Led Fan 12cm ATX', '2018-03-08', 10, 752.80, 5);

-- Inserindo dados na tabela CLiente
INSERT INTO cliente(nome_completo, cpf, data_nascimento, email, telefone)
    VALUES  ('Pedro Braga', '13056167789','1996-10-26','pedrin@rockin.com','22654788'),
            ('Tanjiro Kamado', '13056637844','2010-10-31','slayer13mizu1@hashira.com','22654777'),
            ('Lara Croft', '13056636656','1997-06-22','archeologyrocks@gmail.com','55524588'),
            ('James Hetfield', '13056637024','1979-04-15','metallijames02@tungstenio.com','55524606');

-- Inserindo dados na tabela Endereço
INSERT INTO endereco(cep, rua, numero, complemento, bairro, cidade, uf, id_cliente)
    VALUES  ('25645002', 'Rua alcides penha',  202,'Rua de trás','Centro', 'Frio de Janeiro', 'AM', 1),
            ('25665500', 'rua Luiz Winter', 87, 'A', 'Duarte da Silveira', 'Nova-Petrópolis', 'MG', 2),
            ('26200563', 'rua Visconde de', 41, 'B', 'Alto da Serra', 'Petrópolis', 'RJ', 3),
            ('36587703', 'rua Luiz Pelegrini', 702, 'C', 'Cascatinha', 'Petrópolis', 'RJ', 3),
            ('45210224', 'rua Bartolomeu Sudre', 421, 'A', 'Caxambu', 'Petrópolis', 'RJ', 4);

-- Inserindo dados na tabela Pedidos
INSERT INTO pedido(data_pedido, data_envio, data_entrega, status, id_cliente)
    VALUES  ('2022-05-11', '2022-05-12', '2022-05-19', true, 1),
            ('2022-06-20', '2022-06-23', '2022-06-05', true, 2),
            ('2022-10-02', '2022-10-10', null, false, 3),
            ('2022-10-20', '2022-10-23', null, false, 4);

-- Inserindo dados na tabela ItensPedidos
INSERT INTO item_pedido(percentual_desconto, quantidade, valor_bruto, valor_liquido, id_pedido, id_produto)
    VALUES  (10, 1, 788.15, 709.34, 4, 4),
            (0, 3, 360, 360, 3, 1),
            (0, 1, 27.5, 27.5, 1, 2),
            (0, 1, 833.20, 833.20, 2, 3),
            (0, 1, 788.15, 788.15, 4, 4);