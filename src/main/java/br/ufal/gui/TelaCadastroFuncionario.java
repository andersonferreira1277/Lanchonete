package br.ufal.gui;

import javax.swing.JOptionPane;

import br.ufal.model.Funcionario;
import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TelaCadastroFuncionario extends Application{
	
	private Scene cena;
	@FXML
	private VBox root;
	@FXML
	private TextField textFieldFuncionario;
	@FXML
	private TextField textFieldUsuario;
	@FXML
	private TextField textFieldSenha;
	@FXML
	private TextField textFieldSenhaRepetida;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldEndereco;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnFechar;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("views/TelaCadastroFuncionario.fxml"));
		root = (VBox) loader.load();
		
		
		cena = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Cadastro de Funcionário");
		primaryStage.show();
	}
	public void cadastrarFuncionario(){
		String nomeFuncionario = textFieldFuncionario.getText();
		String usuario = textFieldUsuario.getText();
		
		String senha = Hash256.gerarHash(textFieldSenha.getText());
		String repetirSenha = Hash256.gerarHash(textFieldSenhaRepetida.getText());
		
		String email = textFieldEmail.getText();
		String endereco = textFieldEndereco.getText();
		
		Funcionario funcionario = new Funcionario(nomeFuncionario, usuario, endereco, email, senha);
		
		FuncionarioDAO g = new FuncionarioDAO(conn);
		g.insert(funcionario);
		
	//falta botões
	}
	public void cadastrarFuncionarioDAO(){
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
