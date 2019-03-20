ALTER TABLE tb_usuario_permissao ADD CONSTRAINT fk1_usuario_permissao FOREIGN KEY (codigo_usuario) REFERENCES tb_usuario(codigo);
ALTER TABLE tb_usuario_permissao ADD CONSTRAINT fk2_usuario_permissao FOREIGN KEY (codigo_permissao) REFERENCES tb_permissao(codigo);

ALTER TABLE tb_cidade ADD CONSTRAINT fk1_cidade FOREIGN KEY (codigo_estado) REFERENCES tb_estado(codigo);

ALTER TABLE tb_pessoa ADD CONSTRAINT fk1_pessoa FOREIGN KEY (codigo_usuario) REFERENCES tb_usuario(codigo);
ALTER TABLE tb_pessoa ADD CONSTRAINT fk2_pessoa FOREIGN KEY (codigo_contato) REFERENCES tb_contato(codigo);
ALTER TABLE tb_pessoa ADD CONSTRAINT fk3_pessoa FOREIGN KEY (codigo_cidade) REFERENCES tb_cidade(codigo);
		
ALTER TABLE tb_pedido ADD CONSTRAINT fk1_pedido FOREIGN KEY (codigo_pessoa) REFERENCES tb_pessoa(codigo);

ALTER TABLE tb_produto ADD CONSTRAINT fk1_produto FOREIGN KEY (codigo_categoria) REFERENCES tb_categoria(codigo);
ALTER TABLE tb_produto ADD CONSTRAINT fk2_produto FOREIGN KEY (codigo_fornecedor) REFERENCES tb_fornecedor(codigo);

ALTER TABLE tb_itens_pedidos ADD CONSTRAINT fk1_itens_pedidos FOREIGN KEY (codigo_pedido) REFERENCES tb_pedido(codigo);
ALTER TABLE tb_itens_pedidos ADD CONSTRAINT fk2_itens_pedidos FOREIGN KEY (codigo_produto) REFERENCES tb_produto(codigo) ;