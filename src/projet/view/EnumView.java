package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "systeme/ViewConnexion.fxml" ), //1ère fênetre
	CompteListe			( "compte/ViewCompteListe.fxml" ),
	CompteForm			( "compte/ViewCompteForm.fxml" ),
	
	Accueil				( "accueil/ViewAccueil.fxml" ),
	Participant			( "participant/ViewParticipant.fxml" ),
	ParticipantAjouter  ( "participant/ViewParticipantAjouter.fxml" ),
	ParticipantEquipe   ( "equipe/ViewEquipe.fxml" ),
	ParticipantAttente     ( "participant/ViewParticipantAttente.fxml" ),
	
	Benevole			( "benevole/ViewBenevole.fxml"),
	BenevoleAjouter     ( "benevole/ViewBenevoleAjouter.fxml" ),
	Poste			    ( "benevole/ViewPoste.fxml"),
	PosteLocalisation	( "benevole/ViewLocalisationPoste.fxml"),
	BenevoleEditer      ( "benevole/ViewBenevoleEditer.fxml" ),
	BenevoleAttente     ( "benevole/ViewBenevoleAttente.fxml" ),
	
	;

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
