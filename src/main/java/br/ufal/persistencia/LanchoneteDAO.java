package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LanchoneteDAO {
	//Classe para verificar existencia de tabelas, caso não exista cria as tabelas
	
	public static void verificarTabelas (Connection conn, FuncionarioDAO funcionarioDB) {
		//Verifica se existe as tabelas no banco de dados
		funcionarioDB.verificarTabelas(conn);
	}
	
}
