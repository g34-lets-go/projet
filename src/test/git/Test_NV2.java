package test.git;

public class Test_NV2 {
	
	
	public void bonjour() {
		System.out.println( "Bonsoir !" );
		System.out.println( "Ca va ?" );
	}
	
	
	private String[] adresses = {
			"14 Rue Mozart, Montreuil",
			"77 Rue Picasso, Toulouse", 
			"53 Rue des fleurs, Brive",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
