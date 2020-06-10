package projet.report;


public enum EnumReport implements IEnumReport {

	
	// Valeurs
	
	ParticipantListeSimple ("participant/listeSimple.jrxml"),
	;

	
	// Champs
	
	private String		path;

	
	// Constructeur 
	
	EnumReport( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}

}
