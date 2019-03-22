package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Facture;

public class TestTesterFormat {
	
	String[] factureTest;
	int result;

	@Test
	public void testBonneFacture() {
		factureTest = new String[5];
		result = -1;
		assertEquals(result, Facture.testerFormat(factureTest));
	}
	
	@Test
	public void testMauvaisPrix(){
		factureTest = new String[5];
		result = 0;
		assertEquals(result, Facture.testerFormat(factureTest));
	}
	
	@Test
	public void testMauvaisFormat() {
		factureTest = new String[5];
		result = 1;
		assertEquals(result, Facture.testerFormat(factureTest));
	}
	
	@Test
	public void testMauvaisClient() {
		factureTest = new String[5];
		result = 2;
		assertEquals(result, Facture.testerFormat(factureTest));
	}
	
	@Test
	public void testMauvaisPlat() {
		factureTest = new String[5];
		result = 3;
		assertEquals(result, Facture.testerFormat(factureTest));
	}
}
