-- Criando as tabelas:

CREATE TABLE categoria (id_categoria SERIAL PRIMARY KEY,
nome_categoria varchar(255) NOT NULL,
descricao varchar(255));

CREATE TABLE cliente (id_cliente SERIAL PRIMARY KEY,
nome_completo VARCHAR (50),
cpf VARCHAR (11) UNIQUE,
email VARCHAR (50) UNIQUE,
telefone VARCHAR (11),
data_nascimento DATE
); 

CREATE TABLE endereco (id_endereco SERIAL PRIMARY KEY, 
cep VARCHAR(8) NOT NULL,
rua VARCHAR(50) NOT NULL,
bairro VARCHAR(40) NOT NULL,
cidade VARCHAR(40),
complemento VARCHAR(50),
numero INTEGER NOT NULL,
uf VARCHAR(2),
id_cliente BIGINT, FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente));

CREATE TABLE pedido (id_pedido SERIAL PRIMARY KEY,
data_entrega DATE,
data_envio DATE,
data_pedido DATE NOT NULL,
status BOOLEAN,
id_cliente BIGINT, FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente));


CREATE TABLE produto (id_produto SERIAL PRIMARY KEY,
nome varchar(40) NOT NULL,
descricao varchar(70) NOT NULL UNIQUE,
data_cadastro date,
qtd_estoque integer NOT NULL,
valor_unitario DOUBLE PRECISION NOT NULL,
imagem NVARCHAR(MAX),
id_categoria BIGINT, FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria));

CREATE TABLE item_pedido (id_item_pedido SERIAL PRIMARY KEY,
percentual_desconto DOUBLE PRECISION,
quantidade INTEGER NOT NULL,
valor_bruto DOUBLE PRECISION,
valor_liquido DOUBLE PRECISION,
 id_pedido BIGINT, FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
 id_produto BIGINT,  FOREIGN KEY (id_produto) REFERENCES produto(id_produto));






