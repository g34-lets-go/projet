package projet.view.participant;
import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;
import projet.view.EnumView;

public class ControllerParticipantAjouter {
	
	@FXML private TextField		nomC;	
	@FXML private TextField		prenomC;	
	@FXML private DatePicker	dateNC;	
	@FXML private TextField		telC;	
	@FXML private TextField		adresseC;
	@FXML private TextField		emailC;	
	@FXML private CheckBox		attestationC;
	
	@FXML private TextField		nomE;	
	@FXML private TextField		prenomE;	
	@FXML private DatePicker	dateNE;	
	@FXML private TextField		telE;	
	@FXML private TextField		adresseE;
	@FXML private TextField		emailE;
	@FXML private CheckBox		attestationE;

	@FXML private ToggleGroup		toggleGroupChoixBol;
	@FXML private RadioButton	choixMiniBol;
	@FXML private RadioButton	choixGrandBol;
	
	@FXML private TextField		repasSupp;

	@Inject	private IManagerGui			managerGui;
	@Inject private ModelParticipant modelParticipant;
	
	
	public void initialize() {
		
		Participant courantCapitain = modelParticipant.getCourantCapitain();
		Participant courantEquipier = modelParticipant.getCourantEquipier();
		
		// Capitain
		nomC.textProperty().bindBidirectional( courantCapitain.nomProperty() );
		prenomC.textProperty().bindBidirectional( courantCapitain.prenomProperty() );

		UtilFX.bindBidirectional( dateNC.getEditor(), courantCapitain.dateNProperty(), new ConverterStringLocalDate() );
		
		telC.textProperty().unbindBidirectional( courantCapitain.telProperty() );
		adresseC.textProperty().bindBidirectional( courantCapitain.adresseProperty() );
		emailC.textProperty().bindBidirectional( courantCapitain.emailProperty() );
		
		attestationC.selectedProperty().bindBidirectional( courantCapitain.attestationProperty() );
		
		// Equipier
		nomE.textProperty().bindBidirectional( courantEquipier.nomProperty() );
		prenomE.textProperty().bindBidirectional( courantEquipier.prenomProperty() );

		UtilFX.bindBidirectional( dateNE.getEditor(), courantEquipier.dateNProperty(), new ConverterStringLocalDate() );
		
		telE.textProperty().unbindBidirectional( courantEquipier.telProperty() );
		adresseE.textProperty().bindBidirectional( courantEquipier.adresseProperty() );
		emailE.textProperty().bindBidirectional( courantEquipier.emailProperty() );
		
		attestationE.selectedProperty().bindBidirectional( courantEquipier.attestationProperty() );
		
		// Choix du bol
		toggleGroupChoixBol = new ToggleGroup();
		choixMiniBol.setToggleGroup(toggleGroupChoixBol);
		choixGrandBol.setToggleGroup(toggleGroupChoixBol);
		
		toggleGroupChoixBol.selectedToggleProperty().addListener( obs -> actualiserStatutDansModele() ) ;
		courantCapitain.frais_payeProperty().addListener(  obs -> actualiserStatutDansVue() );
		courantEquipier.frais_payeProperty().addListener(  obs -> actualiserStatutDansVue() );
		actualiserStatutDansVue();
		

	}
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML public void doParticipant_Ajouter() {
		managerGui.showView( EnumView.ParticipantAjouter);
	}
	
	@FXML public void doEnregistrer() {
		modelParticipant.validerMiseAJour();
		//Equipe equipe = new Equipe(courantCapitain.getId()+"-"+courantEquipier.getId() ,"Team", courantCapitain, courantEquipier, 1, 20);
		managerGui.showView( EnumView.ParticipantAjouter);
	}
	
	@FXML public void doAnnuler() {
		managerGui.showView( EnumView.ParticipantAjouter);
		
	}
	
	private void actualiserStatutDansModele() {
		// Modifie le statut en fonction du bouton radio sélectionné 
		Toggle bouton = toggleGroupChoixBol.getSelectedToggle();
		int statut = toggleGroupChoixBol.getToggles().indexOf( bouton  );
		modelParticipant.getCourantCapitain().setFrais_paye( statut );
		modelParticipant.getCourantEquipier().setFrais_paye( statut );
	}
	
	private void actualiserStatutDansVue() {
		// Sélectionne le bouton radio correspondant au statut
		int choixC = modelParticipant.getCourantCapitain().getFrais_paye();
		int choixE = modelParticipant.getCourantEquipier().getFrais_paye();
		Toggle boutonC = toggleGroupChoixBol.getToggles().get( choixC );
		Toggle boutonE = toggleGroupChoixBol.getToggles().get( choixE );
		toggleGroupChoixBol.selectToggle(  boutonC );
		toggleGroupChoixBol.selectToggle(  boutonE );
	}
}
