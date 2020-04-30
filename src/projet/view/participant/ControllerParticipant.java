package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerParticipant {

	@Inject
	private IManagerGui			managerGui;
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML public void doParticipantAjouter() {
		managerGui.showView( EnumView.ParticipantAjouter);
	}
}
