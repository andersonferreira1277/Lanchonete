package br.ufal.gui;

import br.ufal.model.Funcionario;
import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.LanchoneteDAO;
import br.ufal.persistencia.LanchoneteSqLIteDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaLoginGui extends Application{
	
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
		loader.setLocation(getClass().getResource("views/TelaLoginGuiView.fxml"));
		root = loader.load();
		
		verificarBanco();
		
		cena = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}
	
	public void verificarBanco() {
		//Verificar existencia da tabela
		LanchoneteSqLIteDAO lanchoneteSqLIte = LanchoneteSqLIteDAO.getInstance();
		LanchoneteDAO lanchoneteDb = new LanchoneteDAO();
		FuncionarioDAO funcionarioDB = new FuncionarioDAO();
		
		//Verificando existencia de tabelas
		lanchoneteDb.verificarTabelas(lanchoneteSqLIte.getConnection(), funcionarioDB);
	}
	
	@FXML
	public void fazerLogin() {
		String usuario = textFieldUsuario.getText();
		String senha = Hash256.gerarHash(textFieldSenha.getText());
		
		FuncionarioDAO funcionarioDB = new FuncionarioDAO();
		LanchoneteSqLIteDAO lanchoneteSqLIte = LanchoneteSqLIteDAO.getInstance();
		
		Funcionario usuarioAtual = funcionarioDB.selectFuncionarioByUserName(lanchoneteSqLIte.getConnection(),
				textFieldUsuario.getText());
		
		if ((!(usuarioAtual == null)) && (usuarioAtual.getSenha().equals(senha))) {
				//Acesso concedido, abrindo janela principal
				
				TelaPrincipalGui telaPrincipalGui = new TelaPrincipalGui();
				Stage primaryStage = new Stage();
				try {
					telaPrincipalGui.start(primaryStage);
					Stage stageAtual = (Stage) textFieldUsuario.getScene().getWindow();
					stageAtual.close();
					System.out.println(root);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		} else {
			//acesso negado
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
