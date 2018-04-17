package br.ufal.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Funcionario;

public class FuncionarioDB implements IFuncionarioDb{
	
	public void criarTabelas(Connection conn) {
		String tabelaFuncionarios = "CREATE TABLE `funcionarios` (\n" + 
				"	`codigo_Funcionario`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" + 
				"	`nome_Funcionario`	TEXT NOT NULL,\n" + 
				"	`usuario_Funcionario`	TEXT NOT NULL UNIQUE,\n" + 
				"	`endereco_Funcionario`	TEXT NOT NULL,\n" + 
				"	`email`	TEXT NOT NULL,\n" + 
				"	`senha`	TEXT NOT NULL\n" + 
				");";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(tabelaFuncionarios);
			close(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insert(Connection conn, Funcionario funcionario) {
		
		String sql = "INSERT INTO funcionarios (nome_funcionario, usuario_funcionario, "
				+ "endereco_funcionario, email, senha) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			
			//inserindo os dados
			
			preStmt.setString(1, funcionario.getNomeFuncionario());
			preStmt.setString(2, funcionario.getUsuarioFuncionario());
			preStmt.setString(3, funcionario.getEnderecoFuncionario());
			preStmt.setString(4, funcionario.getEmail());
			preStmt.setString(5, funcionario.getSenha());
			
			//executando no banco de dados
			preStmt.executeUpdate();
			conn.commit();
			close(preStmt);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Funcionario selectFuncionarioByUserName(Connection conn, String usuarioFuncionarioP) {
		Funcionario resultado = null;
		
		String sql = "SELECT * FROM funcionarios WHERE usuario_funcionario=?;";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, usuarioFuncionarioP);
			
			ResultSet rs = preStmt.executeQuery();
			
			while(rs.next()) {
				int codigoFuncionario = rs.getInt("codigo_funcionario");
				String nomeFuncionario = rs.getString("nome_funcionario");
				String usuarioFuncionario = rs.getString("usuario_funcionario");
				String enderecoFuncionario = rs.getString("endereco_funcionario");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				
				resultado = new Funcionario(codigoFuncionario, nomeFuncionario, usuarioFuncionario, 
						enderecoFuncionario, email, senha);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<Funcionario> select(String nomeFuncionario) {
		
		return null;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close (PreparedStatement preStmt) {
		try {
			preStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verificarTabelas(Connection conn) {
		try {
			ArrayList<String> tabelas = new ArrayList<String>();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables("banco_de_dados.db", null, "%", null);
			while (rs.next()) {
				tabelas.add(rs.getString(3));
			}
			
			if (!tabelas.contains("funcionarios")) {
				criarTabelas(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
