package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class HsqldbJdbc implements ConnectDB{
	
	private static String url = "jdbc:hsqldb:file:baseDeDados/db";
	private static String login = "admin";
	private static String senha = "6tr!MFYY";
	
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection(url, login, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
