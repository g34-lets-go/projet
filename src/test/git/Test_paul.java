package test.git;

public class Test_paul {
	
	
	public void bonjour() {
		System.out.println( "Bonjour la team Lest Go ahah..." );
	}
	
	
	private String[] adresses = {
			"14 Rue Mozart, Paris",
			"77 Rue Picasso, Toulouse", 
			"53 Rue des fleurs, Limoges",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return null;
		}
	}
	
}
