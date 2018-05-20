package br.ufal.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.ufal.model.Funcionario;
import br.ufal.model.Hash256;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TelaCadastroFuncionario extends GridPane implements Initializable{

	private Application app;

	@FXML
	private GridPane root;
	@FXML
	private TextField textFieldFuncionario;
	@FXML
	private TextField textFieldUsuario;
	@FXML
	private TextField textFieldSenha;
	@FXML
	private TextField textFieldSenhaRepetida;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldEndereco;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnFechar;

	public TelaCadastroFuncionario(Application app) {
		this.app = app;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TelaCadastroFuncionario.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void cadastrarFuncionario(){
		
		String nomeFuncionario = textFieldFuncionario.getText();
		
		String usuario = textFieldUsuario.getText();
		
		String cargo = comboBox.getValue();

		String senha = Hash256.gerarHash(textFieldSenha.getText());
		String repetirSenha = Hash256.gerarHash(textFieldSenhaRepetida.getText());

		String email = textFieldEmail.getText();
		
		String endereco = textFieldEndereco.getText();

		if (senha.equals(repetirSenha) && cargo != null && !nomeFuncionario.isEmpty() && !usuario.isEmpty() && 
				!email.isEmpty() && !endereco.isEmpty()) {
			Funcionario funcionario = new Funcionario(nomeFuncionario, usuario, cargo, endereco, email, senha);
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
			funcionarioDAO.insert(funcionario);

			JOptionPane.showMessageDialog(null, "Cadastrado", "Informação", JOptionPane.INFORMATION_MESSAGE);
			if (app instanceof PrimeiraTelaDeCadastro) {
				Stage stage = new Stage();
				TelaLoginGui teGui = new TelaLoginGui();
				try {
					teGui.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Stage atual = (Stage) this.getScene().getWindow();
				atual.close();
			} else {
				Stage stage = (Stage) this.getScene().getWindow();
				stage.close();
			}
		} else {
			JOptionPane.showMessageDialog(null, "As senhas digitadas não coincidem.\n"
					+ "E/Ou algum campo está em Branco.", "Informação", JOptionPane.WARNING_MESSAGE);
		}


	}
	
	/**
	 * Fecha a janela de cadastro
	 */
	@FXML
	public void fechar() {
		
		Stage stage = (Stage) this.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		ObservableList<String> list = FXCollections.observableArrayList("Gerente", "Funcionário", "Cozinheiro");
		comboBox.setItems(list);
	}


}
