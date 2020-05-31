package projet.view.equipe;
import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoEquipe;
import projet.data.Equipe;
import projet.data.Participant;
import projet.view.EnumView;

public class ControllerParticipantAjouter {
	
	@FXML private TextField 		idEquipe;
	@FXML private TextField 		nomEquipe;
	
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

	@FXML private ToggleGroup	toggleGroupChoixBol;
	@FXML private RadioButton	choixMiniBol;
	@FXML private RadioButton	choixGrandBol;
	
	@FXML private ToggleGroup	toggleGroupCategorie;
	//@FXML private RadioButton	
	
	@FXML private TextField		repasSupp;

	@Inject	private IManagerGui			managerGui;
	@Inject private ModelEquipe modelParticipant;
	
	@Inject private DaoEquipe	daoEquipe;
	@Inject Equipe 				equipe ;
	
	public void initialize() {
		
		Participant courantCapitain = modelParticipant.getCourantCapitain();
		Participant courantEquipier = modelParticipant.getCourantEquipier();
		
		
		// Capitain
		nomC.textProperty().bindBidirectional( courantCapitain.nomProperty() );
		prenomC.textProperty().bindBidirectional( courantCapitain.prenomProperty() );

		UtilFX.bindBidirectional( dateNC.getEditor(), courantCapitain.dateNProperty(), new ConverterStringLocalDate() );
		
		telC.textProperty().bindBidirectional( courantCapitain.telProperty(), new ConverterStringInteger() );
		adresseC.textProperty().bindBidirectional( courantCapitain.adresseProperty() );
		emailC.textProperty().bindBidirectional( courantCapitain.emailProperty() );
		
		attestationC.selectedProperty().bindBidirectional( courantCapitain.attestationProperty() );
		
		// Equipier
		nomE.textProperty().bindBidirectional( courantEquipier.nomProperty() );
		prenomE.textProperty().bindBidirectional( courantEquipier.prenomProperty() );

		UtilFX.bindBidirectional( dateNE.getEditor(), courantEquipier.dateNProperty(), new ConverterStringLocalDate() );
		
		telE.textProperty().bindBidirectional( courantEquipier.telProperty(), new ConverterStringInteger() );
		adresseE.textProperty().bindBidirectional( courantEquipier.adresseProperty() );
		emailE.textProperty().bindBidirectional( courantEquipier.emailProperty() );
		
		attestationE.selectedProperty().bindBidirectional( courantEquipier.attestationProperty() );
		
		// Choix du bol
		toggleGroupChoixBol = new ToggleGroup();
		//choixMiniBol.setToggleGroup(toggleGroupChoixBol);
		//choixGrandBol.setToggleGroup(toggleGroupChoixBol);
		
		/*toggleGroupChoixBol.selectedToggleProperty().addListener( obs -> actualiserStatutDansModele() ) ;
		courantCapitain.frais_payeProperty().addListener(  obs -> actualiserChoixDansVue() );
		courantEquipier.frais_payeProperty().addListener(  obs -> actualiserChoixDansVue() );
		actualiserChoixDansVue();*/
		

		toggleGroupChoixBol.selectedToggleProperty().addListener( obs -> actualiserStatutDansModele() ) ; 
		courantCapitain.frais_payeProperty().addListener(  obs -> actualiserChoixDansVue() );
		courantEquipier.frais_payeProperty().addListener(  obs -> actualiserChoixDansVue() );
		//actualiserChoixDansVue();

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
		if(nomEquipe.getText() == "" || nomEquipe.getText().isEmpty()) {
			System.out.println("Le nom d'equipe ne dois pas etre vide");
		}else {
			Equipe equipe = new Equipe(nomEquipe.getText(), modelParticipant.getCourantCapitain(), modelParticipant.getCourantEquipier(), 1, 2);
			daoEquipe.inserer(equipe);
		}
		
		modelParticipant.preparerAjouter();
		managerGui.showView( EnumView.ParticipantAjouter);
		
	}
	
	@FXML public void doClub() {		
		managerGui.showView( EnumView.ParticipantEquipe);
	}

	
	@FXML public void doAnnuler() {
		managerGui.showView( EnumView.ParticipantAjouter);
		
	}
	
	private void actualiserStatutDansModele() {
		// Modifie le statut en fonction du bouton radio sélectionné 
		Toggle bouton = toggleGroupChoixBol.getSelectedToggle();
		int choix = toggleGroupChoixBol.getToggles().indexOf( bouton  );
		modelParticipant.getCourantCapitain().setFrais_paye( choix );
		modelParticipant.getCourantEquipier().setFrais_paye( choix );
	}
	
	private void actualiserChoixDansVue() {
		// Sélectionne le bouton radio correspondant au statut
		int choixC = modelParticipant.getCourantCapitain().getFrais_paye();
		int choixE = modelParticipant.getCourantEquipier().getFrais_paye();
		Toggle boutonC = toggleGroupChoixBol.getToggles().get( choixC );
		Toggle boutonE = toggleGroupChoixBol.getToggles().get( choixE );
		toggleGroupChoixBol.selectToggle(  boutonC );
		toggleGroupChoixBol.selectToggle(  boutonE );
	}
	
	/*public void refraichir() {
		nomEquipe.setText(null);
		nomC.setText(null);	
		prenomC.setText(null);
		dateNC.set	
		telC;	
		adresseC;
		emailC;	
		attestationC;
	}*/
	
}
