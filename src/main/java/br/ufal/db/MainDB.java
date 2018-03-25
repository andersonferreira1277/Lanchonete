package br.ufal.db;

public class MainDB {

	public static void main(String[] args) {
		LanchoneteSqLIte sqlite = LanchoneteSqLIte.getInstance();
		LanchoneteDb model = new LanchoneteDb(sqlite.getConnection());
		model.criarTabelas();
	}

}
