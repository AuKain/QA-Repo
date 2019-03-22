package test;
import main.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFacture {
	double a = 10.0;
	
	@Test
	public void testAjouterTaxe() {
		double resultat = 1.15 * a;
		assertEquals(resultat, Facture.ajouterTaxe(a), 0);
	}

	@Test
	public void testVoirSiZero1() {
		
		assertEquals(true, Facture.voirSiZero(0));
	}
	@Test
	public void testVoirSiZero2() {
		
		assertEquals(false, Facture.voirSiZero(1));
	}
	
}