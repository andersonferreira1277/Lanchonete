package br.ufal.persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufal.model.Item;

public class ItemDAO implements IItemDAIO {

	private ConnectDB sgbd;

	private static final String NOME_TABELA = "ITENS";
	private static final String ID_ITEM = "ID_ITEM";
	private static final String DESCRICAO = "DESCRICAO";
	private static final String PRECO = "PRECO";

	public ItemDAO(ConnectDB sgbd) {

		this.sgbd = sgbd;
	}

	@Override
	public void criarTabela() {

		String sql = "CREATE TABLE "+NOME_TABELA+" (\n" + 
				"	"+ID_ITEM+" INTEGER IDENTITY PRIMARY KEY,\n" + 
				"	"+DESCRICAO+" VARCHAR(50) NOT NULL,\n" + 
				"	"+PRECO+" REAL NOT NULL\n" + 
				")";

		try (Connection conn = sgbd.getConnection();
				Statement statement = conn.createStatement()){

			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void verificarTabela() {

		ResultSet rs = null;
		try (Connection conn = sgbd.getConnection();){
			ArrayList<String> tabelas = new ArrayList<String>();
			DatabaseMetaData metaData = conn.getMetaData();
			rs = metaData.getTables(null, null, null, new String[] {"TABLE"});
			while (rs.next()) {
				tabelas.add(rs.getString(3));
			}

			if (!tabelas.contains(NOME_TABELA)) {
				criarTabela();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void insert(Item i) {
		
		String sql = "INSERT INTO "+NOME_TABELA+" ("+DESCRICAO+", "+PRECO+")\n" + 
				"VALUES (?,\n" + 
				"        ?);";
		
		try (Connection conn = sgbd.getConnection();
				PreparedStatement pStatement = conn.prepareStatement(sql)){
			
			pStatement.setString(1, i.getDescricao());
			pStatement.setFloat(2, i.getPreco());
			
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> selectListaDeItens() {
		
		List<Item> lItems = new ArrayList<Item>();
		String sql = "SELECT * FROM "+NOME_TABELA+";";
		
		ResultSet rSet = null;
		
		try (Connection conn = sgbd.getConnection();
				Statement statement = conn.createStatement()){
			
			rSet = statement.executeQuery(sql);
			
			while (rSet.next()) {
				
				int id = rSet.getInt(ID_ITEM);
				String deString = rSet.getString(DESCRICAO);
				float preco = rSet.getFloat(PRECO);
				lItems.add(new Item(id, deString, preco));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rSet != null) {
				try {
					rSet.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return lItems;
	}

	@Override
	public Item selecionarItemPorId(int id) {
		
		Item i = null;
		
		String sql = "SELECT *\n" + 
				"FROM "+NOME_TABELA+"\n" + 
				"WHERE "+ID_ITEM+"=?;";
		ResultSet rSet =  null;
		
		
		
		try (Connection conn = sgbd.getConnection();
				PreparedStatement psStatement = conn.prepareStatement(sql)){
			
			psStatement.setInt(1, id);
			
			rSet = psStatement.executeQuery();
			
			while (rSet.next()) {
				
				i = (new Item(rSet.getInt(ID_ITEM), rSet.getString(DESCRICAO), rSet.getFloat(PRECO)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rSet != null) {
				try {
					rSet.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return i;
	}

	@Override
	public void update(int id, Item i) {
		
		String sql = "UPDATE "+NOME_TABELA+"\n" + 
				"SET "+DESCRICAO+"=?,\n" + 
				"	"+PRECO+"=?\n" + 
				"WHERE "+ID_ITEM+"=?;";
		
		try (Connection conn = sgbd.getConnection();
				PreparedStatement pStatement = conn.prepareStatement(sql)){
			
			pStatement.setString(1, i.getDescricao());
			pStatement.setFloat(2, i.getPreco());
			pStatement.setInt(3, id);
			
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void apagarItem(Item i) {
		
		String sql = "DELETE\n" + 
				"FROM "+NOME_TABELA+"\n" + 
				"WHERE "+ID_ITEM+"=?";
		
		try (Connection conn = sgbd.getConnection();
				PreparedStatement pStatement = conn.prepareStatement(sql)){
			
			pStatement.setInt(1, i.getId());
			
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
