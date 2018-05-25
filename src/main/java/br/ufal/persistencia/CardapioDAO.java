package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Cardapio;


public class CardapioDAO {
	
		
		//nomes das colunas como estão escritas no banco de dados
	private final String TABLE_NAME = "CARDAPIO";
	private final String CODIGO_ITEM = "codigo_item";
	private final String ITEM_CARDAPIO = "item_cardapio";
	private final String VALOR_ITEM = "valor_item";
	
		
		private Connection conn;
		
		public CardapioDAO(Connection conn) {
			this.conn = conn;
		}

		public void criarTabelas() {
			String tabelaCardapio = "CREATE TABLE cardapio (\n" + 
					"    codigo_cardapio INTEGER NOT NULL PRIMARY KEY,\n" + 
					"    item_cardapio VARCHAR(50) NOT NULL,\n" + 
					"    valor_item VARCHAR(15) NOT NULL UNIQUE,\n" ;
			
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(tabelaCardapio);
				close(stmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void insert(Cardapio cardapio) {
			
			String sql = "INSERT INTO Cardapio (item_cardapio, valor_item" + "VALUES (?, ?)";
			
			try {
				PreparedStatement preStmt = conn.prepareStatement(sql);
				
				//inserindo os dados
				
				preStmt.setInt(1, cardapio.getCodigo());
				preStmt.setString(2, cardapio.getItem());
				preStmt.setFloat(3, cardapio.getValor());
				
				
				//executando no banco de dados
				preStmt.executeUpdate();
				conn.commit();
				close(preStmt);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public ArrayList<Cardapio> select() {
			
			Cardapio resultado = null;
			ArrayList<Cardapio> nome= new ArrayList<Cardapio>();
			
			String sql = "SELECT * FROM cardapio;";
			
			try {
				PreparedStatement preStmt = conn.prepareStatement(sql);
	
				
				ResultSet rs = preStmt.executeQuery();
				
				while(rs.next()) {
					int codigoItem = rs.getInt(CODIGO_ITEM);
					String item_cardapio = rs.getString(ITEM_CARDAPIO);
					Float valor_cardapio = rs.getFloat(VALOR_ITEM);
					
					
					resultado = new Cardapio(codigoItem, item_cardapio, valor_cardapio);
					nome.add(resultado);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nome;
		
				}

		public void update() {
			// TODO Auto-generated method stub
			
		}

		public void delete() {
			// TODO Auto-generated method stub
			
		}
		
		public void close(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void close (PreparedStatement preStmt) {
			try {
				preStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void verificarTabelas() {
			try {
				ArrayList<String> tabelas = new ArrayList<String>();
				DatabaseMetaData metaData = conn.getMetaData();
				ResultSet rs = metaData.getTables(null, null, null, new String[] {"TABLE"});
				while (rs.next()) {
					tabelas.add(rs.getString(3));
				}
				
				if (!tabelas.contains(TABLE_NAME)) {
					criarTabelas();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	


}
