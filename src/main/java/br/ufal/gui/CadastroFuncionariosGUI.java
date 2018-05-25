package br.ufal.gui;

import br.ufal.model.Funcionario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CadastroFuncionariosGUI extends Application{

	private Scene cena;
	private GridPane gridPane;
	private Funcionario f;
	
	public CadastroFuncionariosGUI() {
		super();
	}
	
	public CadastroFuncionariosGUI(Funcionario f) {
		
		this.f = f;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		if (f != null) {
			gridPane = new TelaCadastroFuncionario(this, f);
		} else {
			gridPane = new TelaCadastroFuncionario(this);
		}

		cena = new Scene(gridPane);

		primaryStage.setScene(cena);
		primaryStage.setTitle("Lanchonete");
		primaryStage.show();
	}

}
