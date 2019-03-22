package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.Facture;

public class TestsMiseEnFormeFacture {

	@Test
	public void testerClients () {
		String client = "Clients :";
		assertEquals( true, Facture.contientClient(client) );
	}
	
	@Test
	public void testerPasClients () {
		String client = "Clients: ";
		assertEquals( false, Facture.contientClient(client) );
	}
	
	@Test
	public void testerPlats () {
		String plats = "Plats :";
		assertEquals( true, Facture.contientPlat(plats) );
	}
	
	@Test
	public void testerPasPlats () {
		String plats = "Plats: ";
		assertEquals( false, Facture.contientPlat(plats) );
	}
	
	@Test
	public void testerCommandes () {
		String commande = "Commandes :";
		assertEquals( true, Facture.contientCommande(commande) );
	}
	
	@Test
	public void testerPasCommandes () {
		String commande = "Commandes: ";
		assertEquals( false, Facture.contientCommande(commande) );
	}
	
	@Test
	public void testerFin () {
		String fin = "Fin";
		assertEquals( true, Facture.contientFin(fin) );
	}
	
	@Test
	public void testerPasFin () {
		String fin = "Fin :";
		assertEquals( false, Facture.contientFin(fin) );
	}
}
