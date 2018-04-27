package br.ufal.lanchonete;

import br.ufal.gui.TelaLoginGui;
import br.ufal.gui.TelaPrincipalGui;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;

public class MainApp {

	public static void main(String[] args) {
		
		HsqldbJdbc hsqldbJdbc = HsqldbJdbc.getInstance();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(hsqldbJdbc.getConnection());
		funcionarioDAO.verificarTabelas();
		TelaLoginGui app = new TelaLoginGui();
		app.main(args);
	}

}
