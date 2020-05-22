package projet.view.benevole;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.sql.DataSource;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ControllerBenevole implements Initializable{
	
// Composants de la vue
	
	@FXML
	private TableView<Benevole>	tableViewBenevoles;
	@FXML
	private TableColumn<Benevole, Integer> columnId;
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
	
	@FXML
	public void viewBenevoles() {
		for ( int i = 0; i<tableViewBenevoles.getItems().size(); i++) {
		    tableViewBenevoles.getItems().clear();
		}
		Connection cn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql;
		try {

			cn=dataSource.getConnection();
			sql=  "SELECT matricule_b,nom,prenom,id_poste FROM benevole";
			stmt=cn.prepareStatement(sql);
			rs=stmt.executeQuery();
			
			while(rs.next()) {				
				donnees.add(new Benevole(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)),  new SimpleStringProperty(rs.getString(4))));
				//donnees.add(new Benevole(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)), rs.getInt(4) ));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		columnId.setCellValueFactory(new PropertyValueFactory<Benevole,Integer>("matBene"));
		columnNom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("nomBene"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("prenomBene"));
		columnAdresse.setCellValueFactory(new PropertyValueFactory<Benevole,String>("AdresseBene"));
		//columnPoste.setCellValueFactory(new PropertyValueFactory<Benevole,Poste>("posteBene"));
		tableViewBenevoles.setItems(donnees);
	
	}
	
	public void viewBenevoleRecherche(String text1, String text2) {
		for ( int i = 0; i<tableViewBenevoles.getItems().size(); i++) {
		    tableViewBenevoles.getItems().clear();
		}
		Connection cn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql;
		try {

			cn=dataSource.getConnection();
			sql = "SELECT matricule_b,nom,prenom,id_poste FROM benevole WHERE nom LIKE ? OR nom LIKE ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, text1+"%");
            stmt.setObject( 2, text2+"%");
            rs = stmt.executeQuery();
			
			while(rs.next()) {				
				donnees.add(new Benevole(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)),  new SimpleStringProperty(rs.getString(4))));
				//donnees.add(new Benevole(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)), rs.getInt(4) ));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		columnId.setCellValueFactory(new PropertyValueFactory<Benevole,Integer>("matBene"));
		columnNom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("nomBene"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<Benevole,String>("prenomBene"));
		columnAdresse.setCellValueFactory(new PropertyValueFactory<Benevole,String>("AdresseBene"));
		//columnPoste.setCellValueFactory(new PropertyValueFactory<Benevole,Poste>("posteBene"));
		tableViewBenevoles.setItems(donnees);
	
	}
	
	// Autres champs		
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		@Inject
		private DataSource dataSource;
		
		
		@Override
		public void initialize(URL location, ResourceBundle ressources) {
			// TODO Auto-generated method stub
			
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
	
	@FXML public void doPoste() {
		managerGui.showView( EnumView.Poste);
	}
	
	@FXML
	public void doBenevoleAjouter() {
		modelBenevole.preparerAjouter();;
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
			if ( managerGui.showDialogConfirm("Etes-vous sûr de voulir supprimer cette personne ?" ) ) {
				modelBenevole.supprimer( item );
				viewBenevoles();
				managerGui.showView( EnumView.Benevole);
			}
			
		}
	}
	
	@FXML
	private void doRechercher() {
		//int matricule = Integer.parseInt(textFieldRecherche.getText());
		System.out.println("Je vais commencer la recherche \n");
		viewBenevoleRecherche(textFieldRecherche.getText(),textFieldRecherche.getText().toUpperCase());
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
	
	
	// Méthodes auxiliaires
/*	
		private void configurerBoutons() {
			
	    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
				buttonEditer.setDisable(true);
				//buttonSupprimer.setDisable(true);
			} else {
				buttonEditer.setDisable(false);
				//buttonSupprimer.setDisable(false);
			}
		}
*/
}

