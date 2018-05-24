package br.ufal.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufal.model.Item;
import br.ufal.persistencia.HsqldbJdbc;
import br.ufal.persistencia.ItemDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FuncionarioGUI extends Application implements Initializable{
	@FXML
	private Scene cena;
	@FXML
	private VBox layout;

	@FXML
	private TableView<Item> tabela;

	@FXML
	private TableColumn<Item, String> colunaDescricao;

	@FXML
	private TableColumn<Item, Float> colunaValor;

	@FXML
	private Button buttonAdicionar;

	@FXML
	private Button buttonExcluir;

	@FXML
	private ComboBox<String> comboBoxPedido;
	
	List<Item> i;
	List<Item> listaParaTabela = FXCollections.observableArrayList();

	@FXML
	void ExcluirProduto(ActionEvent event) {
		
		listaParaTabela.remove(tabela.getSelectionModel().getSelectedItem());
	}

	@FXML
	void adicionarProduto() {
		
		String selecao = comboBoxPedido.getValue();
		for (Item k : i) {
			if (k.getDescricao().equals(selecao)) {
				listaParaTabela.add(k);
			}
		}
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/TelaFuncionario.fxml"));
		loader.setController(this);
		loader.load();
		cena = new Scene(layout);

		primaryStage.setScene(cena);
		primaryStage.show();

	}
	public void configurarTabela() {
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colunaValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tabela.setItems((ObservableList<Item>) listaParaTabela);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurarTabela();
		carregarComboBox();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void carregarComboBox() {
		//carregar todos os pedidos do combobox
		ItemDAO listaItens = new ItemDAO(new HsqldbJdbc());
		i = listaItens.selectListaDeItens();
		List<String> listaString = new ArrayList<String>();
		for (Item k: i) {
			listaString.add(k.getDescricao());
		}
		ObservableList<String> list = FXCollections.observableArrayList(listaString);
		comboBoxPedido.setItems(list);
	}

}