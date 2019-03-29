package test;

import static org.junit.Assert.*;

import org.junit.*;

import main.Facture;

public class TestTesterFormat {

	String[] factureTest = { "Clients :", "Roger", "Céline", "Steeve", "Plats :", "Poutine 10.5", "Frites 2.5",
			"Repas_Poulet 15.75", "Commandes :", "Roger Poutine 1", "Céline Frites 2", "Céline Repas_Poulet 1",
			"Fin", };
	static String facture;
	int result;

	@BeforeClass
	public static void avant() {
		facture = new String();
	}

	@Test
	public void testBonneFacture() {
		result = -1;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );

	}

	@Test
	public void testMauvaisPrix() {
		factureTest[5] = "Poutine &/!";
		result = 0;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );
	}

	@Test
	public void testMauvaisFormat() {
		factureTest[11] = "Céline Repas_Poulet 1%";
		result = 1;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );
	}

	@Test
	public void testMauvaisClient() {
		factureTest[11] = "Simon Repas_Poulet 1";
		result = 2;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );
	}

	@Test
	public void testMauvaisPlat() {
		factureTest[11] = "Céline Soupe_Poulet_Et_Nouilles 1";
		result = 3;
		assertEquals( result, Facture.testerFormat( factureTest, facture ) );
	}

	@AfterClass
	public static void apres() {
		facture = null;
	}
}
