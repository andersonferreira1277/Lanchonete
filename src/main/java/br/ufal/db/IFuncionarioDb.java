package br.ufal.db;

import java.sql.Connection;
import java.util.List;

import br.ufal.model.Funcionario;

public interface IFuncionarioDb {
	public void criarTabelas(Connection conn);
	public void insert(Connection conn, Funcionario funcionario);
	public <E> List<E> select(String nomeFuncionario);
	public Funcionario selectFuncionarioByUserName(Connection conn, String usuarioFuncionario);
	public void update();
	public void delete();
}
