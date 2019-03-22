package main;

import java.util.ArrayList;

public class Facture {

	public static final String CLIENT = "Clients :", PLAT = "Plats :", COMMANDE = "Commandes :", FIN = "Fin";
	private ArrayList<String> clients = new ArrayList<>();
	private ArrayList<Plats> plats = new ArrayList<>();
	private String[][] commandes;

	public Facture( String[] facture ) {
		int i = 0;
		int j = 0;

		if ( contientClient( facture[0] ) ) {

			i++;

			while ( !contientPlat(facture[i]) ) {
				clients.add( facture[i] );

				i++;
			}
			commandes = new String[clients.size()][2];

			i++;

			while ( !contientCommande(facture[i]) ) {
				String[] temp = facture[i].split( "\u0020" );

				plats.add( new Plats( temp[0], Double.parseDouble( temp[1] ) ) );

				i++;
			}

			i++;

			while ( !contientFin(facture[i]) ) {

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
}