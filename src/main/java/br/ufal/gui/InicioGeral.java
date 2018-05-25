package br.ufal.gui;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InicioGeral extends Application{
	
	@FXML
	private VBox vBoxCentral;
	
	@FXML
	private VBox layoutProprio;
	
	private Parent parent;
	
	private Scene cena;
	
	public InicioGeral(Parent parent) {
		this.parent = parent;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("views/InicioGeral.fxml"));
		fxLoader.setController(this);
		fxLoader.load();
		vBoxCentral.getChildren().add(parent);
		
		cena = new Scene(layoutProprio);
		
		primaryStage.setMinWidth(670);
		primaryStage.setMinHeight(471);
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Lanchonete");
		primaryStage.show();
	}
	
}
