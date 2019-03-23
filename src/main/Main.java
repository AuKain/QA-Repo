package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Main {

	public static void main( String[] args ) {

		try {

			new Facture( ( Files.readAllLines( new File( "facture.txt" ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
		} catch ( IOException e ) {
		}
		System.out.println( "\nFin du programme." );
 
		   


	}
}