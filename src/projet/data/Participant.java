package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {

	// Proprietes
	
	private Property<Integer>	id			= new SimpleObjectProperty<>();
	private StringProperty	nom = new SimpleStringProperty();
	private StringProperty	prenom	= new SimpleStringProperty();
	private final Property<LocalDate>	dateN	= new SimpleObjectProperty<>();
	private final Property<Integer>	tel = new SimpleObjectProperty<>();
	private final StringProperty	adresse	= new SimpleStringProperty();
	private final StringProperty	email 		= new SimpleStringProperty();
	private final Property<Boolean> attestation = new SimpleObjectProperty<>(false);
	
	private Property<Integer> frais_paye = new SimpleObjectProperty<>(0);
	private final Property<Integer> repasSup = new SimpleObjectProperty<>();
	private final Property<Integer> idVelo = new SimpleObjectProperty<>();
	private final Property<Boolean> valider = new SimpleObjectProperty<>(true);
	
	public Participant() {
		super();
	
	}
	
	public Participant(Property<Integer> idParti,StringProperty nomParti,StringProperty prenomParti,Property<Integer> frais_payeParti) {
		
		super();
		this.id = idParti;
		this.nom = nomParti;
		this.prenom = prenomParti;
		this.frais_paye = frais_payeParti; 
	}
	
	// getters setters
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	
	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	
	public final Property<LocalDate> dateNProperty() {
		return this.dateN;
	}
	
	public final LocalDate getDateN() {
		return this.dateNProperty().getValue();
	}
	
	public final void setDateN(final LocalDate dateN) {
		this.dateNProperty().setValue(dateN);
	}
	
	public final Property<Integer> telProperty() {
		return this.tel;
	}
	
	public final Integer getTel() {
		return this.telProperty().getValue();
	}
	
	public final void setTel(final Integer tel) {
		this.telProperty().setValue(tel);
	}
	
	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	
	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	
	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	public final Property<Boolean> attestationProperty() {
		return this.attestation;
	}
	
	public final Boolean getAttestation() {
		return this.attestationProperty().getValue();
	}
	
	public final void setAttestation(final Boolean attestation) {
		this.attestationProperty().setValue(attestation);
	}
	
	public final Property<Integer> frais_payeProperty() {
		return this.frais_paye;
	}
	
	public final int getFrais_paye() {
		return this.frais_payeProperty().getValue();
	}
	
	public final void setFrais_paye(final int frais_paye) {
		this.frais_payeProperty().setValue(frais_paye);
	}
	
	public final Property<Integer> repasSupProperty() {
		return this.repasSup;
	}
	
	public final Integer getRepasSup() {
		return this.repasSupProperty().getValue();
	}
	
	public final void setRepasSup(final Integer repasSup) {
		this.repasSupProperty().setValue(repasSup);
	}
	
	public final Property<Integer> idVeloProperty() {
		return this.idVelo;
	}
	
	public final Integer getIdVelo() {
		return this.idVeloProperty().getValue();
	}
	
	public final void setIdVelo(final Integer idVelo) {
		this.idVeloProperty().setValue(idVelo);
	}
	
	
	
}
