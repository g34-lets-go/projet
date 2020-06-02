package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerPoste {
	
	// Composants de la vue
	
		@FXML
		private TableView<Poste>	tableViewPostes;
		@FXML
		private TableColumn<Poste, Integer> columnId;
		@FXML
		private TableColumn<Poste, String> columnNom;
//		@FXML
//		private TableColumn<Poste, Benevole> columnBenevole;
		@FXML
		private TableColumn<Poste, String> columnDescription;
		@FXML
		private TableColumn<Poste, Integer> columnNbActuel;
		@FXML
		private TableColumn<Poste, Integer> columnPersonnel;
		@FXML
		private TextField	textFieldRecherche;
		
		
		ObservableList<Poste> donnees = FXCollections.observableArrayList();  
		
			

	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste			modelPoste;
	
	
	public void initialize() {
		
		tableViewPostes.setItems(modelPoste.getListe());
		columnId.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("id_poste"));
		columnNom.setCellValueFactory(new PropertyValueFactory<Poste,String>("nom_poste"));
		columnDescription.setCellValueFactory(new PropertyValueFactory<Poste,String>("description_poste"));
		columnNbActuel.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("personnel_actuel"));
		columnPersonnel.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("personnel"));
		
	}
	
	public void refresh() {
		if(textFieldRecherche.getText()==null || textFieldRecherche.getText().isEmpty() )
			modelPoste.actualiserListe();
		else
			modelPoste.rechercher(textFieldRecherche.getText());
//		UtilFX.selectInListView( listView, modelBenevole.getCourant() );
//		listView.requestFocus();
	}
	
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
	public void doBenevoleAjouter() {
		//managerGui.showView( EnumView.BenevoleAjouter);
	}

	@FXML
	private void doRechercher() {
		modelPoste.rechercher(textFieldRecherche.getText());
		managerGui.showView( EnumView.Poste);
	}
	
	@FXML
	private void doLocalisation() {
		managerGui.showView( EnumView.PosteLocalisation);
	}
	
}
