package br.ufal.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.ufal.model.Item;
import br.ufal.persistencia.HsqldbJdbc;
import br.ufal.persistencia.ItemDAO;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ItemCadastroGui extends Application implements Initializable{
	
	private Scene cena;
	
	@FXML
    private GridPane layout;
	
	@FXML
	private TextField textDescricao;

	@FXML
	private TextField textPreco;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnCadastrar;
	
	private Item i;
	
	private AdministradorGUI a;
	
	public ItemCadastroGui(AdministradorGUI a) {
		
		this(null, a);
	}
	
	public ItemCadastroGui(Item i, AdministradorGUI a) {
		
		this.a = a;
		this.i = i;
		
		FXMLLoader fLoader = new FXMLLoader(getClass().getResource("views/ItemCadastro.fxml"));
		fLoader.setController(this);
		try {
			fLoader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@FXML
	void cadastrarItem() {
		
		if (!(textDescricao.getText().equals("")) && !(textPreco.getText().equals(""))) {
			
			ItemDAO iDao = new ItemDAO(new HsqldbJdbc());
			
			if (i == null) {
				iDao.insert(new Item(textDescricao.getText(), Float.parseFloat(textPreco.getText())));
			} else {
				iDao.update(i.getId(), (new Item(textDescricao.getText(), Float.parseFloat(textPreco.getText()))));
			}
			JOptionPane.showMessageDialog(null, "Cadastrado", "Info", JOptionPane.INFORMATION_MESSAGE);
			a.carregarTabelaItens();
			cancelar();
		} else {
			JOptionPane.showMessageDialog(null, "Campo(s) em branco", "Info", JOptionPane.WARNING_MESSAGE);
		}

	}

	@FXML
	void cancelar() {
		
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		cena = new Scene(layout);
		
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Cadastro de produto");
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Se item na variavel i, os dados do item vão para os text'sField
		if (i != null) {
			textDescricao.setText(i.getDescricao());
			textPreco.setText(Float.toString(i.getPreco()));
			btnCadastrar.setText("Salvar modificações");
		}
		
		textPreco.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, 
					String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					textPreco.setText(oldValue);
				}
		    }
		});
	}


}
