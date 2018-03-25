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
		
	}
	
	public void criarTabelas () {
		Lanches.criarTabela();
	}
}
