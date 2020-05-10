package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {

	private final Property<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty	nomc = new SimpleStringProperty();
	private final StringProperty	prenomc	= new SimpleStringProperty();
	private final StringProperty	dateNc = new SimpleStringProperty();
	private final Property<Integer>	telc = new SimpleObjectProperty<>();
	private final StringProperty	adressec	= new SimpleStringProperty();
	private final StringProperty	emailc 		= new SimpleStringProperty();
	
	private final StringProperty	nome = new SimpleStringProperty();
	private final StringProperty	prenome	= new SimpleStringProperty();
	private final StringProperty	dateNe = new SimpleStringProperty();
	private final Property<Integer>	tele = new SimpleObjectProperty<>();
	private final StringProperty	adressee	= new SimpleStringProperty();
	private final StringProperty	emaile 		= new SimpleStringProperty();
	
	
	
	
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
	
	public final StringProperty dateNcProperty() {
		return this.dateNc;
	}
	
	public final String getDateNc() {
		return this.dateNcProperty().get();
	}
	
	public final void setDateNc(final String dateNc) {
		this.dateNcProperty().set(dateNc);
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
	
	public final StringProperty dateNeProperty() {
		return this.dateNe;
	}
	
	public final String getDateNe() {
		return this.dateNeProperty().get();
	}
	
	public final void setDateNe(final String dateNe) {
		this.dateNeProperty().set(dateNe);
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

	public final Property<Integer> idProperty() {
		return this.id;
	}
	

	public final Integer getId() {
		return this.idProperty().getValue();
	}
	

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	
	
	
}
