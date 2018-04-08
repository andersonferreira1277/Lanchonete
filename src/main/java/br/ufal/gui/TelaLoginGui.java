package br.ufal.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaLoginGui extends Application{
	
	private Scene cena;
	private VBox root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("views/TelaLoginGuiView.fxml"));
		root = loader.load();
		
		cena = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
