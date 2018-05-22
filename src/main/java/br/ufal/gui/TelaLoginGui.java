package br.ufal.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaLoginGui extends Application implements Initializable{
	
	private Scene cena;
	@FXML
	private VBox root;
	@FXML
	private TextField textFieldUsuario;
	@FXML
	private TextField textFieldSenha;
	@FXML
	private Button btnEntrar;
	@FXML
	private ImageView imagem;

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
		
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
		
		
		if (funcionarioDAO.verificarLogin(usuario, senha)) {
				//Acesso concedido, abrindo janela principal
				
				InicioGeral iGeral = new InicioGeral(new AdministradorGUI());
				Stage newStage = new Stage();
				try {
					iGeral.start(newStage);
					Stage stageAtual = (Stage) textFieldUsuario.getScene().getWindow();
					stageAtual.close();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		File file = new File("imagens/lanchonete.png");
		Image img = new Image(file.toURI().toString());
		imagem.setImage(img);
		
	}
	

}
