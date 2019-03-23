package main;

import java.util.ArrayList;

public class Facture {

	public static final String CLIENT = "Clients :", PLAT = "Plats :", COMMANDE = "Commandes :", FIN = "Fin";
	private ArrayList<String> clients = new ArrayList<>();
	private ArrayList<Plats> plats = new ArrayList<>(); 
	private String[][] commandes;
	public static final double TAXE_TPS = 0.05, TAXE_TVQ = 0.10;

	public Facture( String[] facture ) {
		int i = 0;
		int j = 0;

		if ( contientClient( facture[0] ) ) {

			i++;

			while ( !contientPlat( facture[i] ) ) {
				clients.add( facture[i] );

				i++;
			}
			commandes = new String[clients.size()][2];

			i++;

			while ( !contientCommande( facture[i] ) ) {
				String[] temp = facture[i].split( "\u0020" );

				plats.add( new Plats( temp[0], Double.parseDouble( temp[1] ) ) );

				i++;
			}

			i++;

			while ( !contientFin( facture[i] ) ) {

				double prix = 0;

				int nbPlat = Integer.parseInt( facture[i].split( "\u0020" )[2] );

				double prixSimple = 0; 

				for ( Plats plats : plats ) {

					if ( plats.getNom().compareTo( facture[i].split( " " )[1] ) == 0 ) {
						prixSimple = plats.getCout();

						break;
					}
				}
				prix = nbPlat * prixSimple;
				if ( j == 0 ) {
					commandes[j][0] = facture[i].split( "\u0020" )[0];
					commandes[j][1] = prix + "";
					j++;
				} else {
					boolean dejaLa = false;

					for ( int k = 0; k < commandes.length; k++ ) {

						try {
							if ( facture[i].split( " " )[0].compareTo( commandes[k][0] ) == 0 ) {

								double temp = Double.parseDouble( commandes[k][1] ) + prix;

								commandes[k][1] = temp + "";

								dejaLa = true;
								break;
							}
						} catch ( Exception e ) {
						}
					}
					if ( !dejaLa ) {
						commandes[j][0] = facture[i].split( "\u0020" )[0];
						commandes[j][1] = prix + "";
					}
					j++;
				}
				i++;
			}
		
			afficherCommande(); 
		} else {
			System.out.println( "Le fichier facture n'est pas valide." );
		}
	}

	public static boolean contientClient( String client ) {

		return CLIENT.compareTo( client ) == 0;
	}

	public static boolean contientPlat( String plat ) {

		return PLAT.compareTo( plat ) == 0;
	}

	public static boolean contientCommande( String commande ) {

		return COMMANDE.compareTo( commande ) == 0;
	}

	public static boolean contientFin( String fin ) {

		return FIN.compareTo( fin ) == 0;
	}

	public static double ajouterTaxe( double prix ) {
		return prix * ( 1.0 + TAXE_TPS ) + prix * TAXE_TVQ;
	}

	public static boolean voirSiZero( double prix ) {
		return prix == 0;
	}

	private void afficherCommande() {

		System.out.println();
		boolean present;

		for ( int k = 0; k < clients.size(); k++ ) {

			present = false;

			for ( int j = 0; j < commandes.length; j++ ) {
				try {

					if ( clients.get( k ).compareTo( commandes[j][0] ) == 0 ) {
						present = true;
						break;
					}

				} catch ( NullPointerException e ) {

				}

			}
			if ( !present ) {
				System.out.println( clients.get( k ) + " 0$" );
			}
		}

		for ( int i = 0; i < commandes.length; i++ ) {
			if ( !( commandes[i][0] == null || commandes[i][1] == null ) ) {
				System.out.println( commandes[i][0] + " " + commandes[i][1] + "$" );
			}
		}
	}

	public static int testerFormat(String[] facture){
		int retour = -1;
		ArrayList<String> clientsTemp = new ArrayList<String>();
		ArrayList<String> platsTemp = new ArrayList<String>();
		ArrayList<String> nomCommande = new ArrayList<String>();
		ArrayList<String> platsCommande = new ArrayList<String>(); 
		double[] prixTemp = new double[15];
		double[] combien = new double[15];
		int i = 1;
		while ( facture[i].compareToIgnoreCase(PLAT) != 0){
			clientsTemp.add(facture[i]);
			i++;	
		}
		i++;
		int j = 0;
		while (facture[i].compareToIgnoreCase(COMMANDE) != 0 ){
			platsTemp.add(facture[i].split(" ")[0]);
			try{
				prixTemp[j] = Double.parseDouble((facture[i].split(" ")[1]));
			}
			catch (NumberFormatException e){ 
				retour = 0;
			}
			
			j++;
			i++;
		}
		i++;
		int k = 0;
		while (facture[i].compareToIgnoreCase(FIN) != 0){
			nomCommande.add(facture[i].split(" ")[0]);
			platsCommande.add(facture[i].split(" ")[1]);
			try {
				combien[k] = Integer.parseInt(facture[i].split(" ")[2]);
			}
			catch (Exception e){
				retour = 1;
			}
			i++;
			k++;
		}
		boolean trouve = false;
		for (String nom : nomCommande) {  
			trouve = false;
			for (String vraiNom : clientsTemp) {
				if (vraiNom.compareToIgnoreCase(nom) == 0){ 
					trouve = true;
				}
			}
			if (!trouve){
				retour = 2;
				break;
			}
		}
		boolean trouvePlat = false;
		for (String plat : platsCommande) {
			trouvePlat = false;
			for (String  vraiPlat : platsTemp) {
				if (plat.compareToIgnoreCase(vraiPlat) == 0){
					trouvePlat = true;
				}
			}
			if (!trouvePlat){
				retour = 3;;
			}
		}
		
		return retour;
	}



}