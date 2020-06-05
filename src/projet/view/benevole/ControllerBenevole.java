package projet.view.benevole;



import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
//import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;

public class ControllerBenevole {
	
// Composants de la vue
	
	@FXML
	private TableView<Benevole>	tableViewBenevoles;
//	@FXML
//	private TableColumn<Benevole, Integer> columnId;
	@FXML
	private TableColumn<Benevole, String> columnNom;
	@FXML
	private TableColumn<Benevole, String> columnPrenom;
	@FXML
	private TableColumn<Benevole, String> columnAdresse;
//	@FXML
//	private TableColumn<Benevole, Poste> columnPoste;
	@FXML
	private TextField	textFieldRecherche;
	
	
	
	ObservableList<Benevole> donnees = FXCollections.observableArrayList();  
	
	
	

	// Autres champs		
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;		
		
		
		public void initialize() {
			textFieldRecherche.clear();
			tableViewBenevoles.setItems(modelBenevole.getListe());
//			columnId.setCellValueFactory(new PropertyValueFactory<Benevole,Integer>("matBene"));
			columnNom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("nomBene"));
			columnPrenom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("prenomBene"));
			columnAdresse.setCellValueFactory(new PropertyValueFactory<Benevole,String>("AdresseBene"));
			//columnPoste.setCellValueFactory(new PropertyValueFactory<Benevole,Poste>("posteBene"));
			
		}
		
		public void refresh() {
			if(textFieldRecherche.getText()==null || textFieldRecherche.getText().isEmpty() )
				modelBenevole.actualiserListe();
			else
				modelBenevole.rechercher(textFieldRecherche.getText());
//			UtilFX.selectInListView( listView, modelBenevole.getCourant() );
//			listView.requestFocus();
		}

		
    //Actions
	
	@FXML 
	public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML 
	public void doParticipant() {
	managerGui.showView( EnumView.Participant);
	}
	
	@FXML 
	public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML 
	public void doPoste() {
		managerGui.showView( EnumView.Poste);
	}
	
	@FXML 
	public void doBenevoleAttente() {
		managerGui.showView( EnumView.BenevoleAttente);
	}
	
	@FXML
	public void doBenevoleAjouter() {
		modelBenevole.preparerAjouter();
		managerGui.showView( EnumView.BenevoleAjouter );
	}

	@FXML
	public void doBenevoleEditer() {
		Benevole item = tableViewBenevoles.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelBenevole.preparerModifier(item);
			managerGui.showView( EnumView.BenevoleEditer );
		}
	}

	@FXML
	private void doSupprimer() {
		Benevole item = tableViewBenevoles.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			if ( managerGui.showDialogConfirm("Etes-vous sûr de vouloir supprimer ce bénévole ?" ) ) {
				modelBenevole.supprimer( item );
			//	viewBenevoles();
				managerGui.showView( EnumView.Benevole);
			}
			
		}
	}
	
	@FXML
	private void doRechercher() {
		modelBenevole.rechercher(textFieldRecherche.getText());
		managerGui.showView( EnumView.Benevole);
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( tableViewBenevoles.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doBenevoleEditer();
				}
			}
		}
	}
	

}

