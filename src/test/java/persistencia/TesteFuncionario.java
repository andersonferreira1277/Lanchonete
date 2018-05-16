package persistencia;

import br.ufal.model.Funcionario;
import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;

public class TesteFuncionario {

	public static void main(String[] args) {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
		funcionarioDAO.verificarTabelas();
		/*Funcionario funcionario1 = new Funcionario("Anderson", "anderson", "Rua 1", "andersonferreira1277@gmail.com", Hash256.gerarHash("12345"));
		funcionarioDAO.insert(funcionario1);
		
		Funcionario funcionario2= new Funcionario("Monaly", "monaly", "Rua 2", "monalyvital.ufal@gmail.com", Hash256.gerarHash("12345"));
		funcionarioDAO.insert(funcionario2);
		
		Funcionario funcionario3 = new Funcionario("Allisson", "allison", "Rua 3", "allisson.santos@arapiraca.ufal.br", Hash256.gerarHash("12345"));
		funcionarioDAO.insert(funcionario3);
		*/
		System.out.println(funcionarioDAO.numeroDeFuncionarios());
		
		Funcionario f = funcionarioDAO.selectFuncionarioByUserName("anderson");
		System.out.println("teste"+f.toString());
	}
}
