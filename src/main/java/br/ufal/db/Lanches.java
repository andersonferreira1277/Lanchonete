package br.ufal.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Lanches implements ILanches{
	
	public static void criarTabela() {
		String tabelaLanches = "CREATE TABLE `lanches` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ " `nome_lache` TEXT NOT NULL, `ingredientes` TEXT, `preco` REAL NOT NULL )";
		
		try {
			Statement stmt = LanchoneteDb.getConnection().createStatement();
			stmt.executeUpdate(tabelaLanches);
			stmt.close();
		} catch (SQLException e) {
			//String erro = e.getMessage();
			//System.out.println(erro);
			e.printStackTrace();
		}
	}
	
	public void insert() {
		
		
	}

	public void select() {
		
		
	}

	public void delete() {
		
		
	}

}
