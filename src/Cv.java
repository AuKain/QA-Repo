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

		System.out.println(
				"Nom et prénom : " + this.nom + " " + this.prenom );
		System.out.println( "Formation : " + this.formation );
		System.out.println( "Années d'expérience : " + this.anneesExp );
		System.out.println( "Compétences : " );

		for ( String string : this.competences ) {
			System.out.println( "\t" + string );
		}

		System.out.println( "Attentes : " + this.attentes );
	}

	public String getNom() {
		return nom;
	}

	public void setNom( String nom ) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation( String formation ) {
		this.formation = formation;
	}

	public String getAttentes() {
		return attentes;
	}

	public void setAttentes( String attentes ) {
		this.attentes = attentes;
	}

	public int getAnneesExp() {
		return anneesExp;
	}

	public void setAnneesExp( int anneesExp ) {
		this.anneesExp = anneesExp;
	}

	public String[] getCompetences() {
		return competences;
	}

	public void setCompetences( String[] competences ) {
		this.competences = competences;
	}
}