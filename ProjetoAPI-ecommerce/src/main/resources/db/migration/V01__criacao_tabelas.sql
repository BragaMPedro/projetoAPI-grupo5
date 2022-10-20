-- Criando as tabelas:

CREATE TABLE endereco (id_endereco SERIAL PRIMARY KEY, 
cep varchar(9) NOT NULL, 
rua varchar(100) NOT NULL,
bairro varchar(50) NOT NULL,
cidade varchar(30),
numero INTEGER NOT NULL,
complemento varchar(20),
estado varchar(2));

CREATE TABLE cliente (id_cliente SERIAL PRIMARY KEY,
email varchar(30) NOT NULL,
nome_usuario varchar(20) NOT NULL,
nome_completo varchar(60) NOT NULL,
senha varchar(255),
cpf varchar(14) NOT NULL,
telefone varchar(11),
data_nasc DATE, 
id_endereco INTEGER, FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco));

CREATE TABLE pedido (id_pedido SERIAL PRIMARY KEY,
data_pedido DATE NOT NULL,
data_entrega DATE, 
data_envio DATE,
status VARCHAR(20),
id_cliente INTEGER, FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente));

CREATE TABLE categoria (id_categoria SERIAL PRIMARY KEY, nome varchar(30) NOT NULL, descricao varchar(150));

CREATE TABLE produto (id_produto SERIAL PRIMARY KEY,
nome varchar(30) NOT NULL,
descricao varchar(100),
qtd_estoque INTEGER NOT NULL,
data_cadastro DATE,
valor_unitario FLOAT NOT NULL, 
imagem bytea,
id_categoria INTEGER, FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria));


CREATE TABLE item_pedido (id_item_pedido SERIAL PRIMARY KEY,
quantidade INTEGER NOT NULL,
preco_venda INTEGER NOT NULL,
 id_pedido INTEGER, FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
 id_produto INTEGER,  FOREIGN KEY (id_produto) REFERENCES produto(id_produto));