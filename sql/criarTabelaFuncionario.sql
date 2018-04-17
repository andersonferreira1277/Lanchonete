CREATE TABLE funcionarios (
    codigo_Funcionario INTEGER NOT NULL PRIMARY KEY,
    nome_Funcionario VARCHAR(50) NOT NULL,
    usuario_Funcionario VARCHAR(15) NOT NULL UNIQUE,
    endereco_Funcionario VARCHAR(100) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL);
