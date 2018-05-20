package br.ufal.persistencia;

import java.sql.Connection;
import java.util.List;

import br.ufal.model.Funcionario;

public interface IFuncionarioDAO {
	public void criarTabelas();
	public void insert(Funcionario funcionario);
	public List<Funcionario> selectListaDeFuncionarios();
	public Funcionario selectFuncionarioByUserName(String usuarioFuncionario);
	public void update();
	public void apagarFuncionario(Funcionario f);
}
