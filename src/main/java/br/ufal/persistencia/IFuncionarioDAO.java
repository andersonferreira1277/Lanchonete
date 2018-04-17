package br.ufal.persistencia;

import java.sql.Connection;
import java.util.List;

import br.ufal.model.Funcionario;

public interface IFuncionarioDAO {
	public void criarTabelas();
	public void insert(Funcionario funcionario);
	public <E> List<E> select(String nomeFuncionario);
	public Funcionario selectFuncionarioByUserName(String usuarioFuncionario);
	public void update();
	public void delete();
}
