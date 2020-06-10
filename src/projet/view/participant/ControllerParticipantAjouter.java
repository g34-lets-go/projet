package projet.view.participant;
import javax.inject.Inject;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoEquipe;
import projet.data.Equipe;
import projet.data.Participant;
import projet.view.EnumView;
import projet.view.equipe.ModelEquipe;

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
	@FXML private TextField		prixrepasSupp;

	@Inject	private IManagerGui			managerGui;
	@Inject private ModelParticipant modelParticipant;
	@Inject private ModelEquipe modelEquipe;
	
	private Equipe 		equipe ;
	
	
	public void initialize() {

		equipe = modelEquipe.getCourant();

		Participant courantCapitain = equipe.getCapitaine();
		Participant courantEquipier = equipe.getEquipier();
		
		//Participant courantCapitain = modelParticipant.getCourantCapitain();
		//Participant courantEquipier = modelParticipant.getCourantEquipier();
		
		idEquipe.textProperty().bindBidirectional(equipe.idProperty(), new ConverterStringInteger());
		nomEquipe.textProperty().bindBidirectional(equipe.nomProperty());
		
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
		
		/* Repas supplémentaire*/
		
		StringConverter<String> monConverteur = new StringConverter<String>() {

            @Override
            public String fromString(String repas ) {
                try {
                    int d = Integer.parseInt(repas);
                    d=d*7;
                    return String.format("%d €", d);                    
                } catch (Exception e) {
                    return ("");
                }
            }

 

            @Override
            public String toString(String prixrepas) {
                try {
                    int d = Integer.parseInt(prixrepas) ;
                    d=d/7;
                    return String.format("%d", d);
                } catch (Exception e) {
                    return ("");
                }
            }
        };
        Bindings.bindBidirectional(repasSupp.textProperty(),prixrepasSupp.textProperty(),monConverteur);
		
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
		
<<<<<<< HEAD
		toggleGroupCategorie = new ToggleGroup();
		toggleGroupCategorie.selectedToggleProperty().addListener( obs -> actualiserCategorieDansModel() );
=======
		prixrepasSupp.setText("0 €");
>>>>>>> branch 'master' of https://github.com/g34-lets-go/projet.git

		
	}
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML 
	public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML public void doParticipant_Ajouter() {
		modelParticipant.preparerAjouter();
		managerGui.showView( EnumView.ParticipantAjouter);
	}
	
	@FXML public void doEnregistrer() {
		modelEquipe.validerMiseAJour();
		
		modelEquipe.preparerAjouter();
		managerGui.showDialogMessage("Vous venez d'ajouter une nouvelle équipe !");
		managerGui.showView( EnumView.Participant);
		
	}
	
	@FXML public void doClub() {		
		managerGui.showView( EnumView.ParticipantEquipe);
	}

	
	@FXML public void doAnnuler() {
		managerGui.showView( EnumView.ParticipantAjouter);
		
	}
	
	@FXML public void docalculer() {
		System.out.println("Calul prix");
		Participant val, prix;
		
	}
		
	
	
	private void actualiserStatutDansModele() {
		// Modifie le statut en fonction du bouton radio sélectionné 
		Toggle bouton = toggleGroupChoixBol.getSelectedToggle();
		int choix = toggleGroupChoixBol.getToggles().indexOf( bouton  );
		modelParticipant.getCourantCapitain().setFrais_paye( choix );
		modelParticipant.getCourantEquipier().setFrais_paye( choix );
		//modelEquipe.getCourant().getCapitaine().setFrais_paye(choix);
		//modelEquipe.getCourant().getEquipier().setFrais_paye(choix);
		modelEquipe.getCourant().setIdCourse(choix);
	}
	
	private void actualiserChoixDansVue() {
		// Sélectionne le bouton radio correspondant au statut
		int choixC = modelParticipant.getCourantCapitain().getFrais_paye();
		int choixE = modelParticipant.getCourantEquipier().getFrais_paye();
		Toggle boutonC = toggleGroupChoixBol.getToggles().get( choixC );
		Toggle boutonE = toggleGroupChoixBol.getToggles().get( choixE );
		toggleGroupChoixBol.selectToggle(  boutonC );
		toggleGroupChoixBol.selectToggle(  boutonE );
		
		/*int choixC = modelEquipe.getCourant().getCapitaine().getFrais_paye();
		int choixE = modelEquipe.getCourant().getEquipier().getFrais_paye();
		Toggle boutonC = toggleGroupChoixBol.getToggles().get( choixC );
		Toggle boutonE = toggleGroupChoixBol.getToggles().get( choixE );
		toggleGroupChoixBol.selectToggle(  boutonC );
		toggleGroupChoixBol.selectToggle(  boutonE );*/
	}
	
	private void actualiserCategorieDansModel() {
		Toggle bouton = toggleGroupCategorie.getSelectedToggle();
		int choix = toggleGroupCategorie.getToggles().indexOf( bouton  );
		System.out.println("---------------------"+choix);
		
		modelParticipant.getCourantCapitain().setFrais_paye( choix );
		modelParticipant.getCourantEquipier().setFrais_paye( choix );
		//modelEquipe.getCourant().getCapitaine().setFrais_paye(choix);
		//modelEquipe.getCourant().getEquipier().setFrais_paye(choix);
		modelEquipe.getCourant().setIdCourse(choix);
	}
	
	
}
