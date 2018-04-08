package br.ufal.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class TelaPrincipalGui extends Application{
	
	private VBox root;
	
	private Scene cena;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("views/TelaPrincipalGuiView.fxml"));
		root = loader.load();
		
		cena = new Scene(root);
		
		primaryStage.setScene(cena);
		primaryStage.setTitle("Lanchonete");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
