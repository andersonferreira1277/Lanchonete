package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Funcionario;

public class FuncionarioDAO implements IFuncionarioDAO{
	
	//nomes das colunas como estão escritas no banco de dados
	private final String TABLE_NAME = "FUNCIONARIOS";
	private final String CODIGO_FUNCIONARIO = "CODIGO_FUNCIONARIO";
	private final String NOME_FUNCIONARIO = "NOME_FUNCIONARIO";
	private final String USUARIO_FUNCIONARIO = "USUARIO_FUNCIONARIO";
	private final String ENDERECO_FUNCIONARIO = "ENDERECO_FUNCIONARIO";
	private final String EMAIL = "EMAIL";
	private final String SENHA = "SENHA";
	
	private Connection conn;
	
	public FuncionarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void criarTabelas() {
		String tabelaFuncionarios = "CREATE TABLE FUNCIONARIOS (\n" + 
				"    CODIGO_FUNCIONARIO INTEGER NOT NULL PRIMARY KEY,\n" + 
				"    NOME_FUNCIONARIO VARCHAR(50) NOT NULL,\n" + 
				"    USUARIO_FUNCIONARIO VARCHAR(15) NOT NULL UNIQUE,\n" + 
				"    ENDERECO_FUNCIONARIO VARCHAR(100) NOT NULL,\n" + 
				"    EMAIL VARCHAR(30) NOT NULL UNIQUE,\n" + 
				"    SENHA VARCHAR(64) NOT NULL);";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(tabelaFuncionarios);
			close(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insert(Funcionario funcionario) {
		
		String sql = "INSERT INTO FUNCIONARIOS (NOME_FUNCIONARIO, USUARIO_FUNCIONARIO, "
				+ "ENDERECO_FUNCIONARIO, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?)";
		
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
	
	public Funcionario selectFuncionarioByUserName(String usuarioFuncionarioP) {
		Funcionario resultado = null;
		
		String sql = "SELECT * FROM FUNCIONARIOS WHERE USUARIO_FUNCIONARIO=?;";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, usuarioFuncionarioP);
			
			ResultSet rs = preStmt.executeQuery();
			
			while(rs.next()) {
				int codigoFuncionario = rs.getInt(CODIGO_FUNCIONARIO);
				String nomeFuncionario = rs.getString(NOME_FUNCIONARIO);
				String usuarioFuncionario = rs.getString(USUARIO_FUNCIONARIO);
				String enderecoFuncionario = rs.getString(ENDERECO_FUNCIONARIO);
				String email = rs.getString(EMAIL);
				String senha = rs.getString(SENHA);
				
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
	
	public void verificarTabelas() {
		try {
			ArrayList<String> tabelas = new ArrayList<String>();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(null, null, null, new String[] {"TABLE"});
			while (rs.next()) {
				tabelas.add(rs.getString(3));
			}
			
			if (!tabelas.contains(TABLE_NAME)) {
				criarTabelas();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean verificarLogin(String login, String senha) {
		//recebe login e senha e verifica no banco de dados
		//retorno true caso encontre e false caso não encontre
		Funcionario funcionarioDoBanco = selectFuncionarioByUserName(login);
		
		if ( (login.equals(funcionarioDoBanco.getUsuarioFuncionario()) && 
				(senha.equals(funcionarioDoBanco.getSenha())) )) {
			return true;
		}
		
		return false;
	}
}
