package br.ufal.model;

public class Lanche {
	
	private int id;
	private String nome;
	private String ingredientes;
	private float preco;
	
	
	public Lanche(int id, String nome, String ingredientes, float preco) {
		this.id = id;
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	public Lanche(String nome, String ingredientes, float preco) {
		this(0, nome, ingredientes, preco);
	}

	public int getId() {
		return this.id;
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


	@Override
	public String toString() {
		return "id: "+ this.id + " Nome: " + this.nome + " Ingredientes: " + 
				this.ingredientes+" Preço: "+ this.preco;
	}
	
}
