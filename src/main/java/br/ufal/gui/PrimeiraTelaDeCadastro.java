package br.ufal.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrimeiraTelaDeCadastro extends Application{
	
	private Scene cena;
	private GridPane gridPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		gridPane = new TelaCadastroFuncionario(this);
		
		cena = new Scene(gridPane);
		
		primaryStage.setScene(cena);
		primaryStage.setTitle("Lanchonete");
		primaryStage.show();
	}
	

}
