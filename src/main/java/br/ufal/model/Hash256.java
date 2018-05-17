package br.ufal.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash256 {
	public static String gerarHash(String senha) {
		
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
		String hex = String.format( "%064x", new BigInteger( 1, hash));
		
		return hex;
	}
	
}
