INSERT INTO tb_abastecimento (id, CPFmotorista, dataAbastecimento, quilometragem, combustivel, quantidadeEmLitros, valorPorLitro) VALUES
('01','000.000.000-00','01/02/2020','000100','Diesel','40','R$3.50');

INSERT INTO tb_posto (id, nomeFantasia, telefone, email, CNPJ, endereco) VALUES
('01','Petrobas','8465-5555','petrobas@gmail.com','00.000.000/0001-00','Av Amambai');

INSERT INTO tb_veiculo (id, ano, placa, renavan, patrimonio, chassi, versao, capacidadeTanque, tipoCombustivel) VALUES
('01','2016','ABC1234','123465789','XPTO789','9 BW FJJADJN 8 DNKN NN5','Hilux 2.0','68','Diesel');

INSERT INTO tb_lotacao (id, descricao, endereco, email, site, telefone) VALUES
('01','Batalhão Policia Militar','Av Fatima','navirai@pm.ms.br','www.pm.com.br','3461-5448');

INSERT INTO tb_cidade (id, nome) VALUES
('01','Navirai');

INSERT INTO tb_uf (id, uf) VALUES
('01','MS');

INSERT INTO tb_tipo (id, descricao) VALUES
('01','Caminhonete');

INSERT INTO tb_modelo (id, descricao) VALUES
('01','Hilux Cabine Dupla');

INSERT INTO tb_marca (id, descricao) VALUES
('01','Toyota');