
CREATE TABLE tb_usuario (
		codigo BIGINT(20) PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE tb_permissao (
		codigo BIGINT(20) PRIMARY KEY,
		descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE tb_usuario_permissao (
		codigo_usuario BIGINT(20) NOT NULL,
		codigo_permissao BIGINT(20) NOT NULL,
		PRIMARY KEY (codigo_usuario, codigo_permissao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;	

CREATE TABLE tb_categoria (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_estado (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cidade (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    codigo_estado BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_pessoa (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		cpf BIGINT(20) NOT NULL, 
		data_nascimento DATE NOT NULL,
		sexo VARCHAR(20) NOT NULL,
		logradouro VARCHAR(30),
		numero VARCHAR(30),
		complemento VARCHAR(30),
		bairro VARCHAR(30),
		cep VARCHAR(30),	
		ativo BOOLEAN NOT NULL,
		codigo_usuario BIGINT(20) NOT NULL,
		codigo_contato BIGINT(20) NOT NULL,
        codigo_cidade BIGINT(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
CREATE TABLE tb_contato (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		codigo_pessoa BIGINT(20) NOT NULL,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(100) NOT NULL,
		telefone VARCHAR(20) NOT NULL,
	  FOREIGN KEY (codigo_pessoa) REFERENCES tb_pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

 CREATE TABLE tb_pedido (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		data_pedido DATE NOT NULL,
		data_envio DATE NOT NULL,
		data_entrega DATE NOT NULL,
		valor_total DECIMAL(10,2) NOT NULL,
		frete DECIMAL(10,2) NOT NULL,
		status VARCHAR(100),
		codigo_pessoa BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;	

CREATE TABLE tb_fornecedor (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		razao_social VARCHAR(100) NOT NULL,
		nome_fantasia VARCHAR(100) NOT NULL, 
		cnpj BIGINT(20) NOT NULL,
		logradouro VARCHAR(30),
		numero VARCHAR(30),
		complemento VARCHAR(30),
		bairro VARCHAR(30),
		cep VARCHAR(30),
		cidade BIGINT(20),
		estado BIGINT(20),
		ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_produto (
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		descricao VARCHAR(300) NOT NULL,
		valor DECIMAL(10,2) NOT NULL,
		quantidade BIGINT(5) NOT NULL,
		status VARCHAR(100),
		img1 VARCHAR(200),
		img2 VARCHAR(200),
		img3 VARCHAR(200),
		img4 VARCHAR(200),
		img5 VARCHAR(200),
		codigo_categoria BIGINT(20) NOT NULL,
		codigo_fornecedor BIGINT(20) NOT NULL,
		ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itens_pedidos (
		precoUnitario DECIMAL(10,2) NOT NULL,
		quantidade BIGINT(5) NOT NULL,
		codigo_pedido BIGINT(20) NOT NULL,
		codigo_produto BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
