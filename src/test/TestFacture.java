package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFacture {
	double a = 10;
	@Test
	public void testAjouterTaxe() {
		double resultat = 1.15 * a;  
		assertEquals(resultat, Facture.ajouterTaxe(a));
	}

	@Test
	public void testVoirSiZero1() {
		
		assertEquals(true, Fscture.voirSiZero(0));
	}
	@Test
	public void testVoirSiZero2() {
		
		assertEquals(false, Fscture.voirSiZero(1));
	}
	
}
