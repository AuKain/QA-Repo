package main;

public class Plats {

	private String nom;
	private double cout;

	public Plats( String nom, double cout ) {
		this.nom = nom;
		this.cout = cout;
	}

	public String getNom() {
		return nom;
	}

	public double getCout() {
		return cout;
	}

	@Override
	public String toString() {
		return this.nom;
	}
}