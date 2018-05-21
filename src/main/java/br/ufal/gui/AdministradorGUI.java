package br.ufal.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufal.model.Funcionario;
import br.ufal.persistencia.FuncionarioDAO;
import br.ufal.persistencia.HsqldbJdbc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

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

	private ObservableList<Funcionario> oFuncionarios;

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
	void cadastrarFuncionario() {

		CadastroFuncionariosGUI cFuncionariosGUI = new CadastroFuncionariosGUI();
		Stage stageAtual = (Stage) btnCadastrar.getScene().getWindow();
		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageAtual);

		try {
			cFuncionariosGUI.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editar() {
		
		Funcionario a  = tableFuncionarios.getSelectionModel().getSelectedItem();
		
		CadastroFuncionariosGUI cFuncionariosGUI = new CadastroFuncionariosGUI(a);
		Stage stageAtual = (Stage) btnCadastrar.getScene().getWindow();
		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageAtual);

		try {
			cFuncionariosGUI.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
	void excluir() {

		Funcionario a  = tableFuncionarios.getSelectionModel().getSelectedItem();

		FuncionarioDAO fDao = new FuncionarioDAO(new HsqldbJdbc());

		fDao.apagarFuncionario(a);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarTabelaFuncionarios();
		agendaTarefas();

	}

	public void carregarTabelaFuncionarios() {

		columnId.setCellValueFactory(new PropertyValueFactory<>("codigoFuncionario")); ;

		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));

		columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(new HsqldbJdbc());

		oFuncionarios = FXCollections.observableArrayList(funcionarioDAO.selectListaDeFuncionarios());

		tableFuncionarios.setItems(oFuncionarios);
	}

	public void agendaTarefas() {

		AtualizarTabelaFuncionarios atTabelaFuncionarios = new AtualizarTabelaFuncionarios();
		atTabelaFuncionarios.setPeriod(Duration.seconds(1));
		atTabelaFuncionarios.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {

				ObservableList<Funcionario> value = ((ObservableList<Funcionario>) event.getSource().getValue());
				ObservableList<Funcionario> value2 = tableFuncionarios.getItems();
				
				if (!(value.size() == value2.size()) || !value.equals(value2)) {
					oFuncionarios.removeAll(oFuncionarios);
					oFuncionarios.addAll(value);
					tableFuncionarios.setItems(oFuncionarios);
				}

			}
		});
		atTabelaFuncionarios.start();
	}


}
