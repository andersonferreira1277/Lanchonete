package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HsqldbJdbc {
	
	private static String url = "jdbc:hsqldb:file:baseDeDados/db";
	private static String login = "admin";
	private static String senha = "6tr!MFYY";
	private Connection conn;
	private static HsqldbJdbc instance = new HsqldbJdbc();
	
	private HsqldbJdbc() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection(url, login, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static HsqldbJdbc getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
