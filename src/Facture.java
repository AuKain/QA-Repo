import java.util.ArrayList;

public class Facture {

	public static final String CLIENT = "Clients :", PLAT = "Plats :", COMMANDE = "Commandes :", FIN = "Fin";
	private ArrayList<String> clients = new ArrayList<>();
	private ArrayList<Plats> plats = new ArrayList<>();
	private double[] commandes;
	
	public Facture( String[] facture ) {
		int i = 0;

		if ( CLIENT.compareTo( facture[0] ) == 0 ) {
			//try {
			i++;	
			
				while ( PLAT.compareTo( facture[i] ) != 0 ) {
					clients.add( facture[i] );

					i++;
				}
				
				commandes = new double[clients.size()];
				
				i++;

				while ( COMMANDE.compareTo( facture[i] ) != 0 ) {
					String[] temp = facture[i].split( "\u0020" );
					
//					plats.add( new Plats(temp[0], Double.parseDouble(temp[1])) );TODO probleme avec les parse???

					i++;
				}
				
				i++;

				while ( FIN.compareTo( facture[i] ) != 0 ) {
					double prix = 0;
//					int ind = clients.indexOf( (String) facture[i].split( "\u0020" )[0] );
//					int nbPlat = Integer.parseInt(facture[i].split( "\u0020" )[2]);
//					
//					prix = plats.get( plats.indexOf( new Plats("Poutine", 0) ) ).getCout() * nbPlat;
					
					System.out.println( prix );
					i++;
				}
				
			//} catch ( IndexOutOfBoundsException e ) {
				//System.out.println( "Le fichier facture n'est pas formatté correctement." );
			//}

		} else {
			System.out.println( "Le fichier facture n'est pas valide." );
		}
	}
}