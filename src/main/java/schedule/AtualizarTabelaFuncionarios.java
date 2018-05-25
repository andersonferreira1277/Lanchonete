package schedule;

import br.ufal.model.Funcionario;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class AtualizarTabelaFuncionarios extends ScheduledService<ObservableList<Funcionario>>{

	@Override
	protected Task<ObservableList<Funcionario>> createTask() {
		// TODO Auto-generated method stub
		return new Task<ObservableList<Funcionario>>(
				) {

			@Override
			protected ObservableList<Funcionario> call() throws Exception {
				
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
				ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(funcionarioDAO.selectListaDeFuncionarios());
				return funcionarios;
			}
		};
	}

}
