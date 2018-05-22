package br.ufal.lanchonete;

import br.ufal.gui.PrimeiraTelaDeCadastro;
import br.ufal.gui.TelaLoginGui;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.application.Application;

public class MainApp {

	public static void main(String[] args) {
		
		//Verificando tabela funcionarios
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
		funcionarioDAO.verificarTabelas();
		
		if (funcionarioDAO.numeroDeFuncionarios() < 1) {
			PrimeiraTelaDeCadastro primeiraTelaDeCadastro = new PrimeiraTelaDeCadastro();
			Application.launch(primeiraTelaDeCadastro.getClass(), args);
		} else {
			TelaLoginGui app = new TelaLoginGui();
			Application.launch(app.getClass(), args);
		}
		
		
	}

}
