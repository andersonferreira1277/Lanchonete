package br.ufal.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int numeroPedido;
	private String data;
	private float total;
	private List<Item> lista;
	
	public Pedido() {
		this.data=data;
		this.numeroPedido=numeroPedido;
		this.total=total;
		
	}
	public String getData() {
		return data;
	}
	public int getId() {
		return numeroPedido;
	}
	public void setId(int id) {
		this.numeroPedido = id;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<Item> getLista() {
		return lista;
	}
}

