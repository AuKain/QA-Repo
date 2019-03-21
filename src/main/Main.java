package main;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

import outilsjava.OutilsFichier;

public class Main {

	public static void main( String[] args ) {

		String nomFic = OutilsFichier.lireNomFichier( "Quelle est le nom du fichier: " );
		try {

			new Facture( ( Files.readAllLines( new File( nomFic ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
		} catch ( IOException e ) {
		}

		System.out.println( "\nFin du programme." );
	}
}