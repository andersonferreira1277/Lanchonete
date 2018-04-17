package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LanchoneteSqLIteDAO{
	
	private static String url = "jdbc:sqlite:banco_de_dados.db";
	private Connection conn;
	private static LanchoneteSqLIteDAO instance = new LanchoneteSqLIteDAO();
	
	private LanchoneteSqLIteDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static LanchoneteSqLIteDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	
}
