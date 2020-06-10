package projet.view.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;
import javax.sql.DataSource;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;
import projet.view.EnumView;

public class ControllerParticipantAttente {
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelParticipant	modelParticipant;
	@Inject
	private DataSource dataSource;
	
// Composants de la vue
	
	@FXML
	private TableView<Participant>	tableViewParticipants;
	@FXML
	private TableColumn<Participant, Integer> columnId;
	@FXML
	private TableColumn<Participant, String> columnNom;
	@FXML
	private TableColumn<Participant, String> columnPrenom;
	@FXML
	private TableColumn<Participant, String> columnEngagement;
	@FXML
	private TextField	textFieldRecherche;
	
	
	ObservableList<Participant> donnees = FXCollections.observableArrayList(); 
	
	
	//Actions
		
		
		@FXML public void doAccueil() {
			managerGui.showView( EnumView.Accueil);
			//System.out.println("Aomine");	
		}
		
		@FXML 
		public void doBenevole() {
			managerGui.showView( EnumView.Benevole);
		}
		
		@FXML public void listerParticipant() {
			
			managerGui.showView( EnumView.Participant);
		}
		
		@FXML public void doClub() {		
			managerGui.showView( EnumView.ParticipantEquipe);
		}
			
		@FXML
		public void doParticipantAttente() {
			managerGui.showView( EnumView.ParticipantAttente);
		}
		
		@FXML 
		public void doPoste() {
			managerGui.showView( EnumView.Poste);
		}
		
		@FXML
		public void doParticipantAjouter() {
			modelParticipant.preparerAjouter();;
			managerGui.showView( EnumView.ParticipantAjouter );
		}
		
		@FXML
		private void doAjouter() {
			Participant item = tableViewParticipants.getSelectionModel().getSelectedItem();
			if ( item == null ) {
				managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
			} else {
				if ( managerGui.showDialogConfirm("Etes-vous sûr de vouloir ajouter ce participant ?" ) ) {
					modelParticipant.validation(item);
					managerGui.showView( EnumView.ParticipantAttente);
				}
				
			}
		}
		
		@FXML
		private void doAjouterTous() {
			if ( managerGui.showDialogConfirm("Etes-vous sûr de vouloir ajouter ce(s) participant(s) ?" ) ) {
				for(Participant item : tableViewParticipants.getItems()) {
					modelParticipant.validation(item);
				}
				
			}
			managerGui.showView( EnumView.ParticipantAttente);
		}
		
		@FXML
		private void doRechercher() {
			modelParticipant.rechercher(textFieldRecherche.getText());
			managerGui.showView( EnumView.ParticipantAttente);
		}
		
		public void initialize() {
			tableViewParticipants.setItems(ModelParticipant.getListe());
			columnId.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("id"));
			columnNom.setCellValueFactory(new PropertyValueFactory<Participant,String>("nom"));
			columnPrenom.setCellValueFactory(new PropertyValueFactory<Participant,String>("prenom"));
			columnEngagement.setCellValueFactory(new PropertyValueFactory<Participant,String>("frais_paye"));
			
		}
		
		public void refresh() {
			if(textFieldRecherche.getText()==null || textFieldRecherche.getText().isEmpty() )
				modelParticipant.actualiserListeAttente();
			else
				modelParticipant.rechercher(textFieldRecherche.getText());
		}
	

}

