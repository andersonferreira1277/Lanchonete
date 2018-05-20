package br.ufal.model;

public class Funcionario {
	
	private int codigoFuncionario;
	private String nomeFuncionario;
	private String usuarioFuncionario;
	private String cargo;
	private String enderecoFuncionario;
	private String email;
	private String senha;
	

	public Funcionario(String nomeFuncionario, String usuarioFuncionario,
			String cargo, String enderecoFuncionario, String email, String senha) {
		
		this(-1, nomeFuncionario, usuarioFuncionario, cargo, enderecoFuncionario, email, senha);
	}
	
	public Funcionario(int codigoFuncionario, String nomeFuncionario, String usuarioFuncionario,
			String cargo, String enderecoFuncionario, String email, String senha) {
		
		this.codigoFuncionario = codigoFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.usuarioFuncionario = usuarioFuncionario;
		this.cargo = cargo;
		this.enderecoFuncionario = enderecoFuncionario;
		this.email = email;
		this.senha = senha;
	}

	public void cadastrarFuncionario () {
		
	}
	
	public void alterarFuncionario () {
		
	}
	
	public void consultarFuncionario() {
		
	}
	
	public void excluirFuncionario() {
		
	}

	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public String getUsuarioFuncionario() {
		return usuarioFuncionario;
	}
	
	public String getCargo() {
		return cargo;
	}

	public String getEnderecoFuncionario() {
		return enderecoFuncionario;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
}
