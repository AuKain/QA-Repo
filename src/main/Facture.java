package main;

import java.util.ArrayList;

public class Facture {

	public static final String CLIENT = "Clients :", PLAT = "Plats :", COMMANDE = "Commandes :", FIN = "Fin";
	private ArrayList<String> clients = new ArrayList<>();
	private ArrayList<Plats> plats = new ArrayList<>();
	private String[][] commandes;
	static String erreurFactures = new String( "" );
	public static final double TAXE_TPS = 0.05, TAXE_TVQ = 0.10;

	public Facture( String[] facture ) {
		int i = 0;
		int j = 0;
		testerFormat( facture, erreurFactures );
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

				try {
					plats.add( new Plats( temp[0], Double.parseDouble( temp[1] ) ) );
				} catch ( Exception e ) {
				}
				i++;
			}

			i++;

			while ( !contientFin( facture[i] ) && facture.length < i ) {

				if ( !facture[i].isEmpty() ) {
					double prix = 0;
					int nbPlat = 0;
					try {
						nbPlat = Integer.parseInt( facture[i].split( "\u0020" )[2] );
					} catch ( Exception e ) {

					}
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
				}
				i++;
			}
			erreurFactures += batirCommande();
			System.out.println( erreurFactures );
		} else {
			System.out.println( "Le fichier facture n'est pas valide." );
		}
	}

	private static void setErreurs( String erreurs ) {
		Facture.erreurFactures += erreurs;
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

	private String batirCommande() {
		String factureFinale = new String();
		String commande = new String( "" );
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
				commande += ( clients.get( k ) + " 0$\n" );
			}
		}

		for ( int i = 0; i < commandes.length; i++ ) {
			if ( !( commandes[i][0] == null || commandes[i][1] == null ) ) {
				commande += ( commandes[i][0] + " " + commandes[i][1] + "$\n" );
			}
		}
		return ( factureFinale += commande );
	}

	public static int testerFormat( String[] facture, String erreursFacture ) {
		int retour = -1;
		ArrayList<String> clientsTemp = new ArrayList<String>();
		ArrayList<String> platsTemp = new ArrayList<String>();
		ArrayList<String> nomCommande = new ArrayList<String>();
		ArrayList<String> platsCommande = new ArrayList<String>();
		String categorie = new String();
		double[] prixTemp = new double[15];
		double[] combien = new double[15];
		int k = 0;
		int j = 0;
		for ( int i = 0; i < facture.length; i++ ) {

			switch ( facture[i] ) {
			case PLAT:
				categorie = PLAT;
				break;
			case COMMANDE:
				categorie = COMMANDE;
				break;
			case CLIENT:
				categorie = CLIENT;
				break;
			case FIN:
				categorie = FIN;
				break;

			default:
				if ( categorie.compareToIgnoreCase( CLIENT ) == 0 ) {
					clientsTemp.add( facture[i] );
				}

				if ( categorie.compareToIgnoreCase( PLAT ) == 0 ) {
					platsTemp.add( facture[i].split( " " )[0] );
					try {
						prixTemp[j] = Double.parseDouble( ( facture[i].split( " " )[1] ) );
					} catch ( NumberFormatException e ) {
						erreursFacture += "Erreur le plat : " + platsTemp.get( j )
								+ " ne possède pas un prix valide\n\n";
						facture[i] = "";
						retour = 0;
					}

					j++;
				}

				if ( categorie.compareToIgnoreCase( COMMANDE ) == 0 ) {
					nomCommande.add( facture[i].split( " " )[0] );
					platsCommande.add( facture[i].split( " " )[1] );
					try {
						combien[k] = Integer.parseInt( facture[i].split( " " )[2] );
					} catch ( Exception e ) {
						erreursFacture += "Erreur la commande : " + facture[i]
								+ " n'est pas écrit sous un format valide\n\n";
						facture[i] = "";
						retour = 1;
					}
					k++;
					boolean trouve = false;
					for ( String nom : nomCommande ) {
						trouve = false;
						for ( String vraiNom : clientsTemp ) {
							if ( vraiNom.compareToIgnoreCase( nom ) == 0 ) {
								trouve = true;
							}
						}
						if ( !trouve ) {
							erreursFacture += "Erreur le client : " + nom + " n'est pas un client valide\n\n";
							facture[i] = "";
							retour = 2;
							break;
						}
					}
					boolean trouvePlat = false;
					for ( String plat : platsCommande ) {
						trouvePlat = false;
						for ( String vraiPlat : platsTemp ) {
							if ( plat.compareToIgnoreCase( vraiPlat ) == 0 ) {
								trouvePlat = true;
							}
						}
						if ( !trouvePlat ) {
							erreursFacture += "Erreur le plat : " + plat + " n'est pas un plat valide\n\n";
							facture[i] = "";
							retour = 3;
							;
						}
					}
				}
				break;
			}

		}
		setErreurs( erreursFacture );
		return retour;
	}
}