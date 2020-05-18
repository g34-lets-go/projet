package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {

	private final Property<Integer>	idc			= new SimpleObjectProperty<>();
	private final StringProperty	nomc = new SimpleStringProperty();
	private final StringProperty	prenomc	= new SimpleStringProperty();
	private final Property<LocalDate>	dateNc	= new SimpleObjectProperty<>();
	private final Property<Integer>	telc = new SimpleObjectProperty<>();
	private final StringProperty	adressec	= new SimpleStringProperty();
	private final StringProperty	emailc 		= new SimpleStringProperty();
	private final Property<Boolean> attestationc = new SimpleObjectProperty<>();
	private final Property<Boolean> frais_payec = new SimpleObjectProperty<>();
	private final Property<Integer> repasSupc = new SimpleObjectProperty<>();
	private final Property<Integer> idVeloc = new SimpleObjectProperty<>();

	private final Property<Integer>	ide			= new SimpleObjectProperty<>();
	private final StringProperty	nome = new SimpleStringProperty();
	private final StringProperty	prenome	= new SimpleStringProperty();
	private final Property<LocalDate>	dateNe	= new SimpleObjectProperty<>();
	private final Property<Integer>	tele = new SimpleObjectProperty<>();
	private final StringProperty	adressee	= new SimpleStringProperty();
	private final StringProperty	emaile 		= new SimpleStringProperty();
	private final Property<Boolean> attestatione = new SimpleObjectProperty<>();
	private final Property<Boolean> frais_payee = new SimpleObjectProperty<>();
	private final Property<Integer> repasSupe = new SimpleObjectProperty<>();
	private final Property<Integer> idVeloe = new SimpleObjectProperty<>();
	
	
	
	public final StringProperty nomcProperty() {
		return this.nomc;
	}
	
	public final String getNomc() {
		return this.nomcProperty().get();
	}
	
	public final void setNomc(final String nomc) {
		this.nomcProperty().set(nomc);
	}
	
	public final StringProperty prenomcProperty() {
		return this.prenomc;
	}
	
	public final String getPrenomc() {
		return this.prenomcProperty().get();
	}
	
	public final void setPrenomc(final String prenomc) {
		this.prenomcProperty().set(prenomc);
	}
	
	
	public final Property<Integer> telcProperty() {
		return this.telc;
	}
	
	public final Integer getTelc() {
		return this.telcProperty().getValue();
	}
	
	public final void setTelc(final Integer telc) {
		this.telcProperty().setValue(telc);
	}
	
	public final StringProperty adressecProperty() {
		return this.adressec;
	}
	
	public final String getAdressec() {
		return this.adressecProperty().get();
	}
	
	public final void setAdressec(final String adressec) {
		this.adressecProperty().set(adressec);
	}
	
	public final StringProperty emailcProperty() {
		return this.emailc;
	}
	
	public final String getEmailc() {
		return this.emailcProperty().get();
	}
	
	public final void setEmailc(final String emailc) {
		this.emailcProperty().set(emailc);
	}
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final StringProperty prenomeProperty() {
		return this.prenome;
	}
	
	public final String getPrenome() {
		return this.prenomeProperty().get();
	}
	
	public final void setPrenome(final String prenome) {
		this.prenomeProperty().set(prenome);
	}
	
	
	public final Property<Integer> teleProperty() {
		return this.tele;
	}
	
	public final Integer getTele() {
		return this.teleProperty().getValue();
	}
	
	public final void setTele(final Integer tele) {
		this.teleProperty().setValue(tele);
	}
	
	public final StringProperty adresseeProperty() {
		return this.adressee;
	}
	
	public final String getAdressee() {
		return this.adresseeProperty().get();
	}
	
	public final void setAdressee(final String adressee) {
		this.adresseeProperty().set(adressee);
	}
	
	public final StringProperty emaileProperty() {
		return this.emaile;
	}
	
	public final String getEmaile() {
		return this.emaileProperty().get();
	}
	
	public final void setEmaile(final String emaile) {
		this.emaileProperty().set(emaile);
	}	

	public final Property<Boolean> attestationcProperty() {
		return this.attestationc;
	}
	

	public final Boolean getAttestationc() {
		return this.attestationcProperty().getValue();
	}
	

	public final void setAttestationc(final Boolean attestationc) {
		this.attestationcProperty().setValue(attestationc);
	}
	

	public final Property<Boolean> frais_payecProperty() {
		return this.frais_payec;
	}
	

	public final Boolean getFrais_payec() {
		return this.frais_payecProperty().getValue();
	}
	

	public final void setFrais_payec(final Boolean frais_payec) {
		this.frais_payecProperty().setValue(frais_payec);
	}
	

	public final Property<Integer> repasSupcProperty() {
		return this.repasSupc;
	}
	

	public final Integer getRepasSupc() {
		return this.repasSupcProperty().getValue();
	}
	

	public final void setRepasSupc(final Integer repasSupc) {
		this.repasSupcProperty().setValue(repasSupc);
	}
	

	public final Property<Integer> idVelocProperty() {
		return this.idVeloc;
	}
	

	public final Integer getIdVeloc() {
		return this.idVelocProperty().getValue();
	}
	

	public final void setIdVeloc(final Integer idVeloc) {
		this.idVelocProperty().setValue(idVeloc);
	}
	

	public final Property<Boolean> attestationeProperty() {
		return this.attestatione;
	}
	

	public final Boolean getAttestatione() {
		return this.attestationeProperty().getValue();
	}
	

	public final void setAttestatione(final Boolean attestatione) {
		this.attestationeProperty().setValue(attestatione);
	}
	

	public final Property<Boolean> frais_payeeProperty() {
		return this.frais_payee;
	}
	

	public final Boolean getFrais_payee() {
		return this.frais_payeeProperty().getValue();
	}
	

	public final void setFrais_payee(final Boolean frais_payee) {
		this.frais_payeeProperty().setValue(frais_payee);
	}
	

	public final Property<Integer> repasSupeProperty() {
		return this.repasSupe;
	}
	

	public final Integer getRepasSupe() {
		return this.repasSupeProperty().getValue();
	}
	

	public final void setRepasSupe(final Integer repasSupe) {
		this.repasSupeProperty().setValue(repasSupe);
	}
	

	public final Property<Integer> idVeloeProperty() {
		return this.idVeloe;
	}
	

	public final Integer getIdVeloe() {
		return this.idVeloeProperty().getValue();
	}
	

	public final void setIdVeloe(final Integer idVeloe) {
		this.idVeloeProperty().setValue(idVeloe);
	}

	public final Property<LocalDate> dateNcProperty() {
		return this.dateNc;
	}
	

	public final LocalDate getDateNc() {
		return this.dateNcProperty().getValue();
	}
	

	public final void setDateNc(final LocalDate dateNc) {
		this.dateNcProperty().setValue(dateNc);
	}
	

	public final Property<LocalDate> dateNeProperty() {
		return this.dateNe;
	}
	

	public final LocalDate getDateNe() {
		return this.dateNeProperty().getValue();
	}
	

	public final void setDateNe(final LocalDate dateNe) {
		this.dateNeProperty().setValue(dateNe);
	}

	public final Property<Integer> idcProperty() {
		return this.idc;
	}
	

	public final Integer getIdc() {
		return this.idcProperty().getValue();
	}
	

	public final void setIdc(final Integer idc) {
		this.idcProperty().setValue(idc);
	}
	

	public final Property<Integer> ideProperty() {
		return this.ide;
	}
	

	public final Integer getIde() {
		return this.ideProperty().getValue();
	}
	

	public final void setIde(final Integer ide) {
		this.ideProperty().setValue(ide);
	}
	
	
	
	
	
	
	
	
}
