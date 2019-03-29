package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Scanner;

public class Main {

	public static void main( String[] args ) {
		
		
		try {

			new Facture( ( Files.readAllLines( new File( lireNomFichier() ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
		} catch ( IOException e ) {
			System.out.println( "La facture n'existe pas ou est impossible à lire." );

		}
		System.out.println( "Fin du programme." );

	}

	private static String lireNomFichier() {
		System.out.print( "Entez le nom du fichier de facture : " );
		Scanner scanner = new Scanner( System.in );
		String nomFichier = scanner.nextLine();
		scanner.close();
		return nomFichier;
	}
}