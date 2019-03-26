package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Facture;

public class TestTesterFormat {
	
	String[] factureTest = { "Clients :", "Roger", "C�line", "Steeve", "Plats :", "Poutine 10.5", "Frites 2.5",
			"Repas_Poulet 15.75", "Commandes :", "Roger Poutine 1", "C�line Frites 2", "C�line Repas_Poulet 1",
			"Fin", };
	String facture = new String();
	int result;
	
	@Test
	public void testBonneFacture() {
		result = -1;  
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );    
		
	}

	@Test
	public void testMauvaisPrix() {
		factureTest[5] = "Poutine &/!"; 
		result = 0;
		assertEquals( result, Facture.testerFormat( factureTest, facture  ) );
	}

	@Test
	public void testMauvaisFormat() {
		factureTest[11] = "C�line Repas_Poulet 1%"; 
		result = 1;
		assertEquals( result, Facture.testerFormat( factureTest, facture  ) );
	}

	@Test
	public void testMauvaisClient() {
		factureTest[11] = "Simon Repas_Poulet 1";
		result = 2;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );
	}

	@Test
	public void testMauvaisPlat() {
		factureTest[11] = "C�line Soupe_Poulet_Et_Nouilles 1";
		result = 3;
		assertEquals( result, Facture.testerFormat( factureTest, facture  ) );
	}
}
