package br.ufal.persistencia;

import java.util.List;


import br.ufal.model.Pedido;

public interface IPedidoDAO {
	public void criarTabela();
	public void verificarTabela();
	public void insert(Pedido i);
	public List<Pedido> selectListaDePedidos();
	public Pedido selecionarPedidoPorId(int id);
	public void update(int id, Pedido i);
	public void apagarPedido(Pedido i);
	

}
