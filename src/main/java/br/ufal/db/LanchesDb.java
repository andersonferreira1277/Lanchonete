package br.ufal.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufal.model.Lanche;

public class LanchesDb {
	
	public static void criarTabela() {
		//Criar tabela lanches no banco de dados
		String tabelaLanches = "CREATE TABLE `lanches` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ " `nome_lanche` TEXT NOT NULL, `ingredientes` TEXT, `preco` REAL NOT NULL )";
		
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
	
	public static void insert(Lanche lanche) {
		//Recebe um objeto da classe br.ufal.model.Lanche e inseri no banco de dados 
		
		String sql = "INSERT INTO lanches (nome_lanche, ingredientes, preco ) "
				+ "values (?, ?, ?)";
		
		try {
			PreparedStatement stmt = LanchoneteDb.getConnection().prepareStatement(sql);
			
			//Inserindo os dados no sql
			stmt.setString(1, lanche.getNome());
			stmt.setString(2, lanche.getIngredientes());
			stmt.setFloat(3, lanche.getPreco());
			//end
			
			stmt.executeUpdate();
			LanchoneteDb.getConnection().commit();
			stmt.close();
			
		} catch (Exception e) {
			
		}
	}

	public void select() {
		
		
	}

	public void delete() {
		
		
	}

}
