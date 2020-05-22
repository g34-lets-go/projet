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
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerPoste implements Initializable{
	
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
		
		
		
		ObservableList<Poste> donnees = FXCollections.observableArrayList();  
		
		@FXML
		public void viewPostes() {
			for ( int i = 0; i<tableViewPostes.getItems().size(); i++) {
			    tableViewPostes.getItems().clear();
			}
			
			Connection cn = null;
			PreparedStatement stmt=null;
			ResultSet rs = null;
			String sql;
			try {

				cn=dataSource.getConnection();
				sql=  "SELECT poste.id_poste, poste.nom_poste, poste.description_poste, COUNT(benevole.matricule_b), poste.personnel_poste FROM poste INNER JOIN benevole ON poste.id_poste = benevole.id_poste GROUP BY poste.id_poste ORDER BY poste.id_poste;";
				stmt=cn.prepareStatement(sql);
				rs=stmt.executeQuery();
				
				while(rs.next()) {				
					donnees.add(new Poste( new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)), new SimpleStringProperty(rs.getString(3)),  new SimpleObjectProperty(rs.getInt(4)), new SimpleObjectProperty(rs.getInt(5))));
					//donnees.add(new Benevole(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)), rs.getInt(4) ));
				}

			} catch(Exception e) {
				e.printStackTrace();
			}
			
			columnId.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("id_poste"));
			columnNom.setCellValueFactory(new PropertyValueFactory<Poste,String>("nom_poste"));
			columnDescription.setCellValueFactory(new PropertyValueFactory<Poste,String>("desription_poste"));
			columnNbActuel.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("personnel_actuel"));
			columnPersonnel.setCellValueFactory(new PropertyValueFactory<Poste,Integer>("personnel"));
			
			//columnBenevole.setCellValueFactory(new PropertyValueFactory<Poste,Benevole">("posteBene"));
			tableViewPostes.setItems(donnees);
		
		}
			

	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste	poste;
	@Inject
	private DataSource dataSource;
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void doParticipant() {
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML public void doBenevole() {
		managerGui.showView( EnumView.Benevole);
	}
	
	@FXML public void doBenevoleAjouter() {
		//managerGui.showView( EnumView.BenevoleAjouter);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
