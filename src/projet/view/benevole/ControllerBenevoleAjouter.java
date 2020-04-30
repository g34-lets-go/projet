package projet.view.benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerBenevoleAjouter {
	
	@FXML private TextField		nom;	
	@FXML private TextField		prenom;	
	@FXML private TextField		dateN;	
	@FXML private TextField		tel;	
	@FXML private TextField		adresse;
	@FXML private TextField		email;	
	@FXML private TextField		repasSupp;

	@Inject
	private IManagerGui			managerGui;
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML public void doBenevoleAjouter() {
		//managerGui.showView( EnumView.BenevoleAjouter);
	}
	
	@FXML public void doEnregistrer() {
		//managerGui.showView( EnumView.ParticipantAjouter);
	}
	
	@FXML public void doAnnuler() {
		//managerGui.showView( EnumView.ParticipantAjouter);
	}
}