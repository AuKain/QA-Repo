import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.*;
import outilsjava.OutilsFichier;

public class Main {

	public static void main( String[] args ) {

		System.out.println( "Bienvenue chez Barette!" );
		
		String[] compet = {"Melee", "tendinite", "La Mort."};
		
		Cv cvBarette = new Cv("Barette", "Jérémy", "Secondaire", "$ARGENT$", -1, compet );
		
		cvBarette.affiche();
		
		
		
		String nomFic = OutilsFichier.lireNomFichier("Quelle est le nom du fichier");
		try{
			
			new Facture((Files.readAllLines( new File(nomFic).toPath(), Charset.defaultCharset() )).toArray(new String[]{}));
		}
		catch (Exception e){
		}
		
		System.out.println( "\nFin du programme." );
	}
}