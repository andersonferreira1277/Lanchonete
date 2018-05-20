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
	private static final String TABLE_NAME = "FUNCIONARIOS";
	private static final String CODIGO_FUNCIONARIO = "codigo_funcionario";
	private static final String NOME_FUNCIONARIO = "NOME_FUNCIONARIO";
	private static final String USUARIO_FUNCIONARIO = "USUARIO_FUNCIONARIO";
	private static final String CARGO_FUNCIONARIO = "CARGO_FUNCIONARIO";
	private static final String ENDERECO_FUNCIONARIO = "ENDERECO_FUNCIONARIO";
	private static final String EMAIL = "EMAIL";
	private static final String SENHA = "SENHA";

	private ConnectDB sgbd;

	public FuncionarioDAO(ConnectDB sgbd) {
		this.sgbd = sgbd;
	}

	/**
	 * Cria a tabela FUNCIONARIOS no banco de dados
	 */
	public void criarTabelas() {

		String sql = "CREATE TABLE FUNCIONARIOS (\n" + 
				"    CODIGO_FUNCIONARIO INTEGER IDENTITY PRIMARY KEY,\n" + 
				"    NOME_FUNCIONARIO VARCHAR(50) NOT NULL,\n" + 
				"    USUARIO_FUNCIONARIO VARCHAR(15) NOT NULL UNIQUE,\n" +
				"    CARGO_FUNCIONARIO VARCHAR(20) NOT NULL,\n" +
				"    ENDERECO_FUNCIONARIO VARCHAR(100) NOT NULL,\n" + 
				"    EMAIL VARCHAR(80) NOT NULL UNIQUE,\n" + 
				"    SENHA VARCHAR(64) NOT NULL);";

		try (Connection conn = sgbd.getConnection();
				Statement stmt = conn.createStatement();) {

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * armazena no banco de dados um Funcionário
	 * @param Recebe uma Funcionário e armazena no banco de dados
	 */
	public void insert(Funcionario funcionario) {

		String sql = "INSERT INTO FUNCIONARIOS (NOME_FUNCIONARIO, USUARIO_FUNCIONARIO, CARGO_FUNCIONARIO, "
				+ "ENDERECO_FUNCIONARIO, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = sgbd.getConnection();
				PreparedStatement preStmt = conn.prepareStatement(sql);){


			//inserindo os dados

			preStmt.setString(1, funcionario.getNomeFuncionario());
			preStmt.setString(2, funcionario.getUsuarioFuncionario());
			preStmt.setString(3, funcionario.getCargo());
			preStmt.setString(4, funcionario.getEnderecoFuncionario());
			preStmt.setString(5, funcionario.getEmail());
			preStmt.setString(6, funcionario.getSenha());

			//executando no banco de dados
			preStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Funcionario selectFuncionarioByUserName(String usuarioFuncionarioP) {

		Funcionario resultado = null;

		String sql = "SELECT * FROM FUNCIONARIOS WHERE USUARIO_FUNCIONARIO=?;";

		ResultSet rs = null;

		try (Connection conn = sgbd.getConnection();
				PreparedStatement preStmt = conn.prepareStatement(sql);
				){

			preStmt.setString(1, usuarioFuncionarioP);
			rs = preStmt.executeQuery();


			while(rs.next()) {
				int codigoFuncionario = rs.getInt(CODIGO_FUNCIONARIO);
				String nomeFuncionario = rs.getString(NOME_FUNCIONARIO);
				String usuarioFuncionario = rs.getString(USUARIO_FUNCIONARIO);
				String cargoFuncionario = rs.getString(CARGO_FUNCIONARIO);
				String enderecoFuncionario = rs.getString(ENDERECO_FUNCIONARIO);
				String email = rs.getString(EMAIL);
				String senha = rs.getString(SENHA);

				resultado = new Funcionario(codigoFuncionario, nomeFuncionario, usuarioFuncionario, 
						cargoFuncionario, enderecoFuncionario, email, senha);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return resultado;
	}

	public List<Funcionario> selectListaDeFuncionarios() {

		String sql = "SELECT * FROM "+TABLE_NAME+";";
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		ResultSet rSet = null;

		try (Connection conn = sgbd.getConnection();
				Statement statement = conn.createStatement()){
			rSet = statement.executeQuery(sql);

			while (rSet.next()) {

				int codigoFuncionario = rSet.getInt(CODIGO_FUNCIONARIO);
				String nomeFuncionario = rSet.getString(NOME_FUNCIONARIO);
				String usuarioFuncionario = rSet.getString(USUARIO_FUNCIONARIO);
				String cargoFuncionario = rSet.getString(CARGO_FUNCIONARIO);
				String enderecoFuncionario = rSet.getString(ENDERECO_FUNCIONARIO);
				String email = rSet.getString(EMAIL);
				String senha = rSet.getString(SENHA);

				funcionarios.add(new Funcionario(codigoFuncionario, nomeFuncionario, usuarioFuncionario, 
						cargoFuncionario, enderecoFuncionario, email, senha));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rSet != null) {
				try {
					rSet.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return funcionarios;
	}

	/**
	 * Conta o numero de funcionarios na tabela
	 * @return retorna a quatidade de funcionarios
	 */
	public int numeroDeFuncionarios() {

		int resultado = 0;
		String sql = "SELECT COUNT(*) linhas\n" + 
				"FROM "+TABLE_NAME+";";

		ResultSet rSet = null;

		try (Connection conn = sgbd.getConnection();
				Statement statement = conn.createStatement();){

			rSet = statement.executeQuery(sql);

			while (rSet.next()) {
				resultado = rSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rSet.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return resultado;
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void delete() {
		// TODO Auto-generated method stub

	}


	public void verificarTabelas() {

		ResultSet rs = null;
		try (Connection conn = sgbd.getConnection();){
			ArrayList<String> tabelas = new ArrayList<String>();
			DatabaseMetaData metaData = conn.getMetaData();
			rs = metaData.getTables(null, null, null, new String[] {"TABLE"});
			while (rs.next()) {
				tabelas.add(rs.getString(3));
			}

			if (!tabelas.contains(TABLE_NAME)) {
				criarTabelas();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean verificarLogin(String login, String senha) {
		//recebe login e SENHA e verifica no banco de dados
		//retorno true caso encontre e false caso não encontre
		Funcionario funcionarioDoBanco = selectFuncionarioByUserName(login);

		if ( (login.equals(funcionarioDoBanco.getUsuarioFuncionario()) && 
				(senha.equals(funcionarioDoBanco.getSenha())) )) {
			return true;
		}

		return false;
	}
}
