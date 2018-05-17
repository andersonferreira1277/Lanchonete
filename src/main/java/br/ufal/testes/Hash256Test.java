package br.ufal.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufal.model.Hash256;
import junit.framework.TestCase;

public class Hash256Test extends TestCase {

	Hash256 h;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		h = new Hash256();
	}

	@Test
	public void test() {
		assertEquals("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", h.gerarHash("123")); 
		assertEquals("15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225", h.gerarHash("123456789"));
	}

}
