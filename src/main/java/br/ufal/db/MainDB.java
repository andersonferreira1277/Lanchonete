package br.ufal.db;

import java.util.List;

import br.ufal.model.Lanche;

public class MainDB {

	public static void main(String[] args) {
		LanchoneteSqLIte sqlite = LanchoneteSqLIte.getInstance();
		LanchoneteDb model = new LanchoneteDb(sqlite.getConnection());
		List<Lanche> lista = LanchesDb.select();
		
		for (Lanche e : lista) {
			System.out.println(e);
		}
		
		System.out.println("Atualizando==============");
		
		Lanche l = new Lanche(1, "X-buger-king-subway", "bacon", (float) 60.0);
		System.out.println("Objeto: "+l);
		LanchesDb.update(l);
		
		lista = LanchesDb.select();
		
		for (Lanche e : lista) {
			System.out.println(e);
		}
		
		model.close();
	}

}
