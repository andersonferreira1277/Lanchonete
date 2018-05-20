package br.ufal.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.ufal.model.Funcionario;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdministradorGUI extends TabPane implements Initializable{

	@FXML
	private TableView<Funcionario> tableFuncionarios;

	@FXML
	private TableColumn<Funcionario, Integer> columnId;

	@FXML
	private TableColumn<Funcionario, String> columnNome;

	@FXML
	private TableColumn<Funcionario, String> columnCargo;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	public AdministradorGUI() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TelaInicialAdministrador.fxml"));
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
	void cadastrar() {

	}

	@FXML
	void editar() {

	}

	@FXML
	void excluir() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		carregarTabela();
		
	}
	
	public void carregarTabela() {
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("codigoFuncionario")); ;

		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));

		columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());
		
		ObservableList<Funcionario> oFuncionarios = FXCollections.observableArrayList(funcionarioDAO.selectListaDeFuncionarios());
		
		tableFuncionarios.setItems(oFuncionarios);
	}


}
