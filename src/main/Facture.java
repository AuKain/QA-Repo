package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Facture {

	public static final String CLIENT = "Clients :", PLAT = "Plats :", COMMANDE = "Commandes :", FIN = "Fin";
	private ArrayList<String> clients = new ArrayList<>();
	private ArrayList<Plats> plats = new ArrayList<>();
	private ArrayList<String[]> listeCommandes = new ArrayList<String[]>();
	static String erreurFactures = new String( "" );
	public static final double TAXE_TPS = 0.05, TAXE_TVQ = 0.10;

	public Facture( String[] facture ) {
		String categorie = "";
		testerFormat( facture, erreurFactures );
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
				if ( contientClient( facture[0] ) ) {
					if ( categorie.compareToIgnoreCase( FIN ) != 0 && !facture[i].isEmpty() ) {
						if ( categorie.compareToIgnoreCase( CLIENT ) == 0 ) {
							clients.add( facture[i] );
						}
						if ( categorie.compareToIgnoreCase( PLAT ) == 0 ) {
							String[] temp = facture[i].split( "\u0020" );
							try {
								plats.add( new Plats( temp[0], Double.parseDouble( temp[1] ) ) );
							} catch ( Exception e ) {
							}

						}
						if ( categorie.compareToIgnoreCase( COMMANDE ) == 0 ) {
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
								String[] comm = { facture[i].split( "\u0020" )[0], prix + "" };
								if ( clientCaliss( comm[0] ) == -1 ) {

									listeCommandes.add( comm );

								} else {

									for ( String[] commande : listeCommandes ) {
										try {
											if ( facture[i].split( " " )[0].compareTo( commande[0] ) == 0 ) {

												double temp = Double.parseDouble( commande[1] ) + prix;

												commande[1] = temp + "";

												break;
											}
										} catch ( Exception e ) {
										}
									}
								}
							}
						}
					}
				} else {
					System.out.println( "Le fichier facture n'est pas valide." );
				}
				break;
			}
		}
		if ( categorie.compareToIgnoreCase( FIN ) == 0 ) {
			erreurFactures += batirCommande();
			System.out.println( erreurFactures );
			ecrireFacture( erreurFactures );
		}
	}

	private int clientCaliss( String client ) {
		int existe = -1;

		for ( int i = 0; i < listeCommandes.size(); i++ ) {

			if ( listeCommandes.size() == 0 || listeCommandes.get( i )[0].equalsIgnoreCase( client ) ) {
				existe = i;
				break;
			}
		}

		return existe;
	}

	private void ecrireFacture( String contenu ) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "YYYY-MM-dd_HH-mm" );
		LocalDateTime now = LocalDateTime.now();
		PrintWriter writer;
		try {
			writer = new PrintWriter( "Facture_du_" + dtf.format( now ) + ".txt", "UTF-8" );
			writer.print( contenu );
			writer.close();
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
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
		String commande = new String( "" );
		boolean present;
		String[] commandeTemp = new String[2];

		for ( int k = 0; k < clients.size(); k++ ) {

			present = false;

			for ( int j = 0; j < listeCommandes.size(); j++ ) {

				commandeTemp = listeCommandes.get( j );
				try {

					if ( clients.get( k ).compareTo( commandeTemp[0] ) == 0 ) {
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

		for ( int i = 0; i < listeCommandes.size(); i++ ) {
			commandeTemp = listeCommandes.get( i );
			if ( !( commandeTemp[0] == null || commandeTemp[1] == null ) ) {
				commande += ( commandeTemp[0] + " " + commandeTemp[1] + "$\n" );
			}
		}
		return ( commande );
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
						erreursFacture += "\nErreur le plat : " + platsTemp.get( j )
								+ " ne possède pas un prix valide\n";
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
						erreursFacture += "\nErreur la commande : " + facture[i]
								+ " n'est pas écrit sous un format valide\n";
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
							erreursFacture += "\nErreur le client : " + nom + " n'est pas un client valide\n";
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
							erreursFacture += "\nErreur le plat : " + plat + " n'est pas un plat valide\n";
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