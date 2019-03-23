package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Cv;

public class TestCV {

	@Test
	public void testNomConstr() {
		String[] competances = new String[1];
		competances[0] = "comp1";
		Cv cv = new Cv( "nom", "", "", "", 0, competances );
		cv.setNom( "nom1" );

		assertEquals( "nom1", cv.getNom() );
	}

	@Test
	public void testPrenom() {
		Cv cv = new Cv();
		cv.setPrenom( "prenom" );
		assertEquals( "prenom", cv.getPrenom());
	}

	@Test
	public void testFormation() {
		Cv cv = new Cv();
		cv.setFormation( "formation" );
		assertEquals( "formation", cv.getFormation());
	}
	
	@Test
	public void testAttentes() {
		Cv cv = new Cv();
		cv.setAttentes( "attentes" );
		assertEquals( "attentes", cv.getAttentes());
	}
	
	@Test
	public void testAnneesExp() {
		Cv cv = new Cv();
		cv.setAnneesExp( 2 );
		assertEquals( 2, cv.getAnneesExp());
	}
}