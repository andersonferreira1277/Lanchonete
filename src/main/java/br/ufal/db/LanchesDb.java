package br.ufal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Lanche;

public class LanchesDb {
	
	public static void criarTabela(Connection conn) {
		//Criar tabela lanches no banco de dados
		String tabelaLanches = "CREATE TABLE `lanches` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ " `nome_lanche` TEXT NOT NULL, `ingredientes` TEXT, `preco` REAL NOT NULL )";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(tabelaLanches);
			close(stmt);
		} catch (SQLException e) {
			//String erro = e.getMessage();
			//System.out.println(erro);
			e.printStackTrace();
		}
	}
	
	public static void insert(Connection conn, Lanche lanche) {
		//Recebe um objeto da classe br.ufal.model.Lanche e inseri no banco de dados 
		
		String sql = "INSERT INTO lanches (nome_lanche, ingredientes, preco ) "
				+ "values (?, ?, ?)";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			
			//Inserindo os dados no sql
			preStmt.setString(1, lanche.getNome());
			preStmt.setString(2, lanche.getIngredientes());
			preStmt.setFloat(3, lanche.getPreco());
			//end
			
			preStmt.executeUpdate();
			conn.commit();
			close(preStmt);
			
		} catch (Exception e) {
			
		}
	}

	public static List<Lanche> select(Connection conn) {
		//Retorna todos os lanches do banco de dados
		
		List<Lanche> lanches = new ArrayList<Lanche>();
		String sql = "SELECT * FROM lanches;";
		try {
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);
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
	
	public static void update(Connection conn, Lanche lanche) {
		//Atualiza um registro de lanche
		String sql = "UPDATE lanches set nome_lanche=?, ingredientes=?, preco=? WHERE id=?;";
		
		try {
			//LanchoneteDb.getConnection().setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, lanche.getNome());
			stmt.setString(2, lanche.getIngredientes());
			stmt.setFloat(3, lanche.getPreco());
			stmt.setInt(4, lanche.getId());
			
			stmt.executeUpdate();
			
			conn.commit();
			
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
