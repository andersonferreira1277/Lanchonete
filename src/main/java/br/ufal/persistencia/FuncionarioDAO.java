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
	private final String CODIGO_FUNCIONARIO = "codigo_funcionario";
	private final String NOME_FUNCIONARIO = "nome_funcionario";
	private final String USUARIO_FUNCIONARIO = "usuario_funcionario";
	private final String ENDERECO_FUNCIONARIO = "endereco_funcionario";
	private final String EMAIL = "email";
	private final String SENHA = "senha";
	
	private Connection conn;
	
	public FuncionarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void criarTabelas() {
		String tabelaFuncionarios = "CREATE TABLE funcionarios (\n" + 
				"    codigo_Funcionario INTEGER NOT NULL PRIMARY KEY,\n" + 
				"    nome_Funcionario VARCHAR(50) NOT NULL,\n" + 
				"    usuario_Funcionario VARCHAR(15) NOT NULL UNIQUE,\n" + 
				"    endereco_Funcionario VARCHAR(100) NOT NULL,\n" + 
				"    email VARCHAR(30) NOT NULL UNIQUE,\n" + 
				"    senha VARCHAR(64) NOT NULL);";
		
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
	
	public Funcionario selectFuncionarioByUserName(String usuarioFuncionarioP) {
		Funcionario resultado = null;
		
		String sql = "SELECT * FROM funcionarios WHERE usuario_funcionario=?;";
		
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
