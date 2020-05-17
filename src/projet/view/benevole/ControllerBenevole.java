package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Service;
import projet.view.EnumView;
import projet.view.service.ModelService;

public class ControllerBenevole {
	// Composants de la vue
	    
	    @FXML
		private ListView<Benevole>	listView;
		@FXML
		private Button				buttonEditer;
//		@FXML
//		private Button				buttonSupprimer;
		// Autres champs
		
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		
//		
		// Initialisation du Controller

		@FXML
		private void initialize() {

			// Data binding
			listView.setItems( modelBenevole.getListe() );
			
			listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNomBene() ));
			
			// Configuraiton des boutons
			listView.getSelectionModel().selectedItemProperty().addListener(
					(obs, oldVal, newVal) -> {
						configurerBoutons();
			});
			configurerBoutons();

		}
		
		public void refresh() {
			modelBenevole.actualiserListe();
			UtilFX.selectInListView( listView, modelBenevole.getCourant() );
			listView.requestFocus();
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
	
	@FXML public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML
	public void doBenevoleAjouter() {
		modelBenevole.preparerAjouter();;
		managerGui.showView( EnumView.BenevoleAjouter );
	}
/*
	@FXML
	public void doBenevoleEditer() {
		Service item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelBenevole.preparerModifier(item);
			managerGui.showView( EnumView.BenevoleAjouter );
		}
	}
*/
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
//					doBenevoleEditer();
				}
			}
		}
	}
	// Méthodes auxiliaires
	
		private void configurerBoutons() {
			
	    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
				buttonEditer.setDisable(true);
				//buttonSupprimer.setDisable(true);
			} else {
				buttonEditer.setDisable(false);
				//buttonSupprimer.setDisable(false);
			}
		}
}
