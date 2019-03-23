package main;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Main {

	public static void main( String[] args ) {
		String erreur = new String();
		try {

			new Facture( ( Files.readAllLines( new File( "Facture.txt" ).toPath(), Charset.defaultCharset() ) )
					.toArray( new String[] {} ) );
		} catch ( IOException e ) {
			erreur = "OOOOOOOOOOOOF";
		}
		System.out.println( "\nFin du programme." );
		  
	}
}