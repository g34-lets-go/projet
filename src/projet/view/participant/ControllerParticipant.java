package projet.view.participant;

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
import projet.data.Participant;
import projet.view.EnumView;

public class ControllerParticipant implements Initializable{

	@Inject
	private IManagerGui			managerGui;
	
	
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
	
	ObservableList<Participant> donnees = FXCollections.observableArrayList();  
	
	@Inject
	private DataSource dataSource;
	
	
	@FXML public void doAccueil() {
		managerGui.showView( EnumView.Accueil);
	}
	
	@FXML public void listerParticipant() {
		
		managerGui.showView( EnumView.Participant);
	}
	
	@FXML public void doParticipantAjouter() {
		managerGui.showView( EnumView.ParticipantAjouter);
	}
	
	
	@FXML	public void viewParticipants() {
		for ( int i = 0; i<tableViewParticipants.getItems().size(); i++) {
		    tableViewParticipants.getItems().clear();
		}
		Connection cn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql;
		try {

			cn=dataSource.getConnection();
			sql=  "SELECT matricule_p,nom,prenom,frais_paye FROM participant WHERE valider = true";
			stmt=cn.prepareStatement(sql);
			rs=stmt.executeQuery();
			
			while(rs.next()) {				
				donnees.add(new Participant(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)),  new SimpleObjectProperty(rs.getInt(4))));
				//donnees.add(new Participant(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)), rs.getInt(4) ));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		columnId.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("id"));
		columnNom.setCellValueFactory(new PropertyValueFactory<Participant,String>("nom"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<Participant,String>("prenom"));
		columnEngagement.setCellValueFactory(new PropertyValueFactory<Participant,String>("frais_paye"));
		tableViewParticipants.setItems(donnees);
	
	}
	
	public void viewParticipantRecherche(String text1, String text2) {
		for ( int i = 0; i<tableViewParticipants.getItems().size(); i++) {
		    tableViewParticipants.getItems().clear();
		}
		Connection cn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql;
		try {

			cn=dataSource.getConnection();
			sql = "SELECT matricule_p,nom,prenom,id_velo FROM partcipant WHERE nom LIKE ? OR nom LIKE ? AND valider = true";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, text1+"%");
            stmt.setObject( 2, text2+"%");
            rs = stmt.executeQuery();
			
			while(rs.next()) {				
				donnees.add(new Participant(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)),  new SimpleObjectProperty(rs.getInt(4))));
				//donnees.add(new Participant(  new SimpleObjectProperty<>( rs.getInt(1)) , new SimpleStringProperty(rs.getString(2)) ,  new SimpleStringProperty(rs.getString(3)), rs.getInt(4) ));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		columnId.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("id"));
		columnNom.setCellValueFactory(new PropertyValueFactory<Participant,String>("nom"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<Participant,String>("prenom"));
		columnEngagement.setCellValueFactory(new PropertyValueFactory<Participant,String>("frais_paye"));
		tableViewParticipants.setItems(donnees);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle ressources) {
		// TODO Auto-generated method stub
		
	}
}
