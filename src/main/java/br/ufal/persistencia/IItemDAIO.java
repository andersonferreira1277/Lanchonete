package br.ufal.persistencia;

import java.util.List;

import br.ufal.model.Item;

public interface IItemDAIO {

	public void criarTabela();
	public void verificarTabela();
	public void insert(Item i);
	public List<Item> selectListaDeItens();
	public Item selecionarItemPorId(int id);
	public void update(int id, Item i);
	public void apagarItem(Item i);
}


