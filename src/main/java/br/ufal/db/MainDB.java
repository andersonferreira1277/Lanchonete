package br.ufal.db;

import br.ufal.model.Lanche;

public class MainDB {

	public static void main(String[] args) {
		LanchoneteSqLIte sqlite = LanchoneteSqLIte.getInstance();
		LanchoneteDb model = new LanchoneteDb(sqlite.getConnection());
		Lanche lanche = new Lanche("X-bacon", "Bacon", (float) 17.5);
		LanchesDb.insert(lanche); 
		
		model.close();
	}

}
