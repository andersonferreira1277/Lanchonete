package br.ufal.model;

public class Item {
	
	private int id;
	private String descricao;
	private float preco;
	
	
	public Item(int id, String deString, float preco) {
		
		this.id = id;
		this.descricao = deString;
		this.preco = preco;
	}
	
	public Item(String dString, float preco) {
		
		this(-1, dString, preco);
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public float getPreco() {
		return preco;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Item)) {
			return false;
		}
		
		final Item other = (Item) obj;
		
		if (!(this.getId() == other.getId()) || 
				!(this.getDescricao().equals(other.getDescricao())) ||
				!(this.getPreco() == other.getPreco())) {
			
			return false;
		}
		
		return true;
	}

}
