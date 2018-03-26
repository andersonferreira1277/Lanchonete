package br.ufal.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Lanche;

public class LanchesDb {
	
	public static void criarTabela() {
		//Criar tabela lanches no banco de dados
		String tabelaLanches = "CREATE TABLE `lanches` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ " `nome_lanche` TEXT NOT NULL, `ingredientes` TEXT, `preco` REAL NOT NULL )";
		
		try {
			Statement stmt = LanchoneteDb.getConnection().createStatement();
			stmt.executeUpdate(tabelaLanches);
			close(stmt);
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
			close(stmt);
			
		} catch (Exception e) {
			
		}
	}

	public static List<Lanche> select() {
		//Retorna todos os lanches do banco de dados
		
		List<Lanche> lanches = new ArrayList<Lanche>();
		String sql = "SELECT * FROM lanches;";
		try {
			Statement stmt = LanchoneteDb.getConnection().createStatement();
			LanchoneteDb.getConnection().setAutoCommit(false);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome_lanche");
				String ingredientes = rs.getString("ingredientes");
				float preco = rs.getFloat("preco");
				Lanche lanche = new Lanche(id, nome, ingredientes, preco);
				lanches.add(lanche);
			}
			close(stmt, rs);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lanches;
	}
	
	public static void update(Lanche lanche) {
		//Atualiza um registro de lanche
		String sql = "UPDATE lanches set nome_lanche=?, ingredientes=?, preco=? WHERE id=?;";
		
		try {
			//LanchoneteDb.getConnection().setAutoCommit(false);
			PreparedStatement stmt = LanchoneteDb.getConnection().prepareStatement(sql);
			
			stmt.setString(1, lanche.getNome());
			stmt.setString(2, lanche.getIngredientes());
			stmt.setFloat(3, lanche.getPreco());
			stmt.setInt(4, lanche.getId());
			
			stmt.executeUpdate();
			
			LanchoneteDb.getConnection().commit();
			
			close(stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		
	}
	
	public static void close(Statement stmt) throws SQLException {
		stmt.close();
	}
	
	public static void close(Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		close(stmt);
	}
}
