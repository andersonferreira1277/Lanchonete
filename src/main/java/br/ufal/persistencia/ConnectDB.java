package br.ufal.persistencia;

import java.sql.Connection;

public interface ConnectDB {
	
	public Connection getConnection();

}
