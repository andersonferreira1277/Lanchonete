package br.ufal.gui;

import javax.swing.JOptionPane;

import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import br.ufal.persistencia.LanchoneteDAO;
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
		root = (VBox) loader.load();
		
		
		cena = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}
	
	
	@FXML
	public void fazerLogin() {
		String usuario = textFieldUsuario.getText();
		String senha = Hash256.gerarHash(textFieldSenha.getText());
		
		HsqldbJdbc hsqldbJdbc = HsqldbJdbc.getInstance();
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(hsqldbJdbc.getConnection());
		
		
		if (funcionarioDAO.verificarLogin(usuario, senha)) {
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
			textFieldUsuario.setText("");
			textFieldSenha.setText("");//zerando os compos de login e senha
			
			JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)", 
					"Usuário e/ou senha incorreto(s)", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
