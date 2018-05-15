package br.ufal.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import br.ufal.model.Cardapio;
import br.ufal.model.Hash256;
import br.ufal.persistencia.HsqldbJdbc;
import br.ufal.persistencia.CardapioDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCardapio extends Application{
	
	private Scene cena;
	@FXML
	private VBox root;
	@FXML
	private TextField textFieldUsuario;
	@FXML
	private TextField textFieldSenha;
	@FXML
	private Button btnEntrar;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("views/Tela.fxml"));
		root = (VBox) loader.load();
		
		verificarBanco();
		
		cena = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("");
		primaryStage.show();
	}
	
	public void verificarBanco() {
		
		HsqldbJdbc hsqldbJdbc = HsqldbJdbc.getInstance();
		CardapioDAO cardapioDb = new CardapioDAO(null);
		CardapioDAO lanchoneteDB = new CardapioDAO(hsqldbJdbc.getConnection());
		
		//Verificando existencia de tabelas no banco de dados
		lanchoneteDB.verificarTabelas();
	}
	
	@FXML
	public void mostrarCardapio() {
		
		root.setPadding(new Insets(15, 15, 15, 15));
		root.setAlignment(Pos.CENTER);
	
		
		TableView<Cardapio> tabela = new TableView<>();
		
		ObservableList<Cardapio> dados = FXCollections.observableArrayList(new CardapioDAO(hsqldbJdbc.getConnection()).select());
		
		tabela.setItems(dados);
		
		TableColumn<Cardapio, Integer> colunaCodigo = new TableColumn<>("Código");
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_item"));
		tabela.getColumns().add(colunaCodigo);
		
		TableColumn<Cardapio, String> colunaItem = new TableColumn<>("Item");
		colunaItem.setCellValueFactory(new PropertyValueFactory<>("item_cardapio"));
		tabela.getColumns().add(colunaItem);
		
		TableColumn<Cardapio, String> colunaValor = new TableColumn<>("Valor");
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor_item"));
		tabela.getColumns().add(colunaValor);
			
		GridPane.setConstraints(tabela, 0, 1);
		
		root.getChildren().addAll(tabela);
	}

		
		String usuario = textFieldUsuario.getText();
		String senha = Hash256.gerarHash(textFieldSenha.getText());
		
		HsqldbJdbc hsqldbJdbc = HsqldbJdbc.getInstance();
		
		
		
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}

