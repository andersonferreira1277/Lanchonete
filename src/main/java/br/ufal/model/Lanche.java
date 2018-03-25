package br.ufal.model;

public class Lanche {
	
	private String nome;
	private String ingredientes;
	private float preco;
	
	
	public Lanche(String nome, String ingredientes, float preco) {
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}


	public String getNome() {
		return nome;
	}


	public String getIngredientes() {
		return ingredientes;
	}


	public float getPreco() {
		return preco;
	}
	
	
}
