package projet.view.benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerBenevoleAjouter {
	
//	Composante de la vue
	
	@FXML 
	private TextField		textFieldMatri;
	@FXML 
	private TextField		textFieldNom;	
	@FXML 
	private TextField		textFieldPrenom;	
	@FXML 
	private DatePicker		datePickerDateNais;			
	@FXML 
	private TextField		textFieldAdresse;
	@FXML 
	private TextField		textFieldEmail;
	@FXML 
	private ComboBox<Poste>		comboBoxPoste; //Pas fixe
	@FXML 
	private CheckBox		checkBoxMembre;
	@FXML
	private CheckBox		checkBoxPermis;

//	Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelBenevole 		modelBenevole;
	@Inject
	private ModelPoste			modelPoste;
	
	// Initialisation du controller
	
		public void initialize() {

			Benevole courant = modelBenevole.getCourant();
			
			// Champs simples
			textFieldMatri.textProperty().bindBidirectional( courant.matBeneProperty(), new ConverterStringInteger() );
			textFieldNom.textProperty().bindBidirectional( courant.nomBeneProperty() );
			textFieldPrenom.textProperty().bindBidirectional( courant.prenomBeneProperty() );
			textFieldAdresse.textProperty().bindBidirectional( courant.adresseBeneProperty() );
			textFieldEmail.textProperty().bindBidirectional( courant.emailBeneProperty() );
			

	        
			// Configuration de la combo box
	        
			comboBoxPoste.setItems(  modelPoste.getListe());
	        comboBoxPoste.valueProperty().bindBidirectional( courant.posteBeneProperty() );
		
	        
	        
	        // Permis de conduire
	        checkBoxPermis.selectedProperty().bindBidirectional( courant.permisBeneProperty() );

	        // Permis de conduire
	        checkBoxMembre.selectedProperty().bindBidirectional( courant.membreClubProperty() );
	        
	        //Date de Naissance
	        UtilFX.bindBidirectional( datePickerDateNais.getEditor(), courant.dateNaiBeneProperty(), new ConverterStringLocalDate() );
		
		}
		
//		Refresh
		
		public void refresh() {
			modelPoste.actualiserListe();
		}
	
	
	// Actions
	
	@FXML 
	public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML 
	public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML 
	public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML 
	public void doBenevoleAjouter() {
		//managerGui.showView( EnumView.BenevoleAjouter);
	}
	@FXML 
	public void doBenevoleAttente() {
		managerGui.showView( EnumView.BenevoleAttente);
	}
	
	@FXML 
	public void doEnregistrer() {
		modelBenevole.validerMiseAJour();
		managerGui.showView( EnumView.Benevole );
	}
	
	@FXML 
	public void doAnnuler() {
		managerGui.showView( EnumView.Benevole );
	}
}
