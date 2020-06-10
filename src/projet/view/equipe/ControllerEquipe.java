package projet.view.equipe;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Equipe;
import projet.data.Memo;
import projet.data.Participant;
import projet.view.EnumView;
import projet.view.memo.ModelMemo;
import projet.view.participant.ModelParticipant;

public class ControllerEquipe {
	
	@FXML ListView<Equipe>	listView;
	
	@FXML 	private Button				buttonModifier;
	@FXML	private Button				buttonSupprimer;
	@FXML	private Button				buttonAjouter;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelEquipe			modelEquipe;
	@Inject
	private ModelParticipant			modelParticipant;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelEquipe.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelEquipe.actualiserListe();
		UtilFX.selectInListView( listView, modelEquipe.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML 
	public void doParticipantAttente() {
		managerGui.showView( EnumView.ParticipantAttente);
	}
	
	@FXML 
	public void doPoste() {
		managerGui.showView( EnumView.Poste);
	}
	
	@FXML public void doParticipantAjouter() {
		modelParticipant.preparerAjouter();
		managerGui.showView( EnumView.ParticipantAjouter);
	}

	@FXML
	private void doModifier() {
		Equipe item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelEquipe.preparerModifier(item);
			managerGui.showView( EnumView.ParticipantAjouter );
		}
	}

	@FXML
	private void doSupprimer() {
		Equipe item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelEquipe.supprimer( item );
				managerGui.showDialogMessage("Vous venez de supprimer une équipe !");
				refresh();
			}
		}
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}
}
