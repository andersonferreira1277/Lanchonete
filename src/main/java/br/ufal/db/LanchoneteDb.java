package br.ufal.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LanchoneteDb {
	
	private static Connection conn;
	
	public LanchoneteDb(Connection conn) {
		this.conn = conn;
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public void verificarTabelas () {
		//Verifica se existe as tabelas no banco de dados
		
	}
	
	public void criarTabelas () {
		//Criar todas as tabelas no banco de dados
		LanchesDb.criarTabela();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
