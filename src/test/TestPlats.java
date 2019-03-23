package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Plats;

public class TestPlats {

	@Test
	public void testGetNom() {
		Plats platTest = new Plats("testNom", 58.25);
		assertEquals( "testNom", platTest.getNom() );
	}
	
	@Test
	public void testGetPrix() {
		Plats platTest = new Plats("testNom", 58.25);
		assertEquals( 58.25, platTest.getCout(),0);
	}
	
	@Test
	public void testToString() {
		Plats platTest = new Plats("testNom", 58.25);
		assertEquals( "testNom", platTest.toString() );
	}

}
