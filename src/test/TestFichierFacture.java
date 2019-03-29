package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import main.Facture;

public class TestFichierFacture {

	String badErreur = "OOOOOf";
	static String erreur;

	@BeforeClass
	public static void avant() {
		erreur = new String();
	}

	@Test
	public void testBonFichier() {
		erreur = "";
		try {

			new Facture( ( Files.readAllLines( new File( "facture.txt" ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
			erreur = "";
		} catch ( IOException e ) {
			erreur = badErreur;
		}
		assertEquals( "", erreur );
	}

	@Test
	public void testMauvaisFichier() {
		erreur = "";
		try {

			new Facture( ( Files.readAllLines( new File( "fac%dfture.txt" ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
			erreur = "";
		} catch ( IOException e ) {
			erreur = badErreur;
		}
		assertEquals( badErreur, erreur );
	}

	@AfterClass
	public static void apres() {
		erreur = null;
	}

}
