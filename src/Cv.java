public class Cv {

	public String nom, prenom, formation, attentes;
	public int anneesExp;
	public String[] competences;

	public Cv() {

		nom = "";
		prenom = "";
		formation = "";
		attentes = "";
		anneesExp = 0;
		competences = new String[10];
	}

	public Cv( String nom, String prenom, String formation, String attentes, int anneesExp, String[] competences ) {

		this.nom = nom;
		this.prenom = prenom;
		this.formation = formation;
		this.attentes = attentes;
		this.anneesExp = anneesExp;
		this.competences = competences;
	}

	public void affiche() {
		
		System.out.println( this.nom + "\n" + this.prenom + "\n" + this.formation + "\nAnnées d'expérience : " + this.anneesExp );
		
		for ( String string : this.competences ) {
			System.out.println( string );
		}
		
		System.out.println( this.attentes );
	}
}