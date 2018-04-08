package br.ufal.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LanchoneteDb {
	//Classe para verificar existencia de tabelas, caso não exista cria as tabelas
	
	public static void verificarTabelas (Connection conn, FuncionarioDB funcionarioDB) {
		//Verifica se existe as tabelas no banco de dados
		funcionarioDB.verificarTabelas(conn);
	}
	
}
