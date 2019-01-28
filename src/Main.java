public class Main {

	public static void main( String[] args ) {

		System.out.println( "Bienvenue chez Barette!" );
		
		String[] compet = {"Melee", "tendinite", "La Mort."};
		
		Cv cvBarette = new Cv("Barette", "Jérémy", "Secondaire", "$ARGENT$", -1, compet );
		
		cvBarette.affiche();
		
		System.out.println( "\nFin du programme." );
	}
}