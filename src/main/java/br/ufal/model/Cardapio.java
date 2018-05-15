package br.ufal.model;

public class Cardapio {
	private int codigo;
	private String item;
	private float valor;
	
	public Cardapio(int codigo, String item, float valor){
		this.codigo=codigo;
		this.item=item;
		this.valor=valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getItem() {
		return item;
	}

	public float getValor() {
		return valor;
	}
	
	

}
