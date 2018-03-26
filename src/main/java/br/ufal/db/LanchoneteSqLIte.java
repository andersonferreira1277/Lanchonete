package br.ufal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LanchoneteSqLIte{
	
	private static String url = "jdbc:sqlite:banco_de_dados.db";
	private Connection conn;
	private static LanchoneteSqLIte instance = new LanchoneteSqLIte();
	
	private LanchoneteSqLIte() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static LanchoneteSqLIte getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	
}
