package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Benevole {
	
// Propriétés

	private  Property<Integer>	matBene 	= new SimpleObjectProperty<>();
	private  StringProperty	nomBene 		= new SimpleStringProperty();
	private  StringProperty	prenomBene		= new SimpleStringProperty();
	private final Property<LocalDate>	dateNaiBene	= new SimpleObjectProperty<>();
	private  StringProperty	adresseBene		= new SimpleStringProperty();
	private final StringProperty	emailBene		= new SimpleStringProperty();
	private final Property<Poste>	 posteBene		= new SimpleObjectProperty<>();
	private final Property<Boolean> permisBene 	= new SimpleObjectProperty<>(false);
	private final Property<Boolean> membreClub 	= new SimpleObjectProperty<>(false);
	private final Property<Boolean> valider 	= new SimpleObjectProperty<>(true);
	

	//@Inject	public DaoPoste daoPoste;
	
//constructeurs
	public Benevole() {
		super();
	}
	
	public Benevole(Property<Integer> mat,StringProperty nom,StringProperty prenom,StringProperty adresse) {
		
		super();
		this.matBene = mat;
		this.nomBene=nom;
		this.prenomBene=prenom;
		this.adresseBene=adresse;
		//this.setPosteBene(daoPoste.retrouverPoste(id_poste)); 
	}
	
//	Getters et setters 
	
	public final Property<Integer> matBeneProperty() {
		return matBene;
	}

	public final Integer getMatBene() {
		return matBene.getValue();
	}
	
	public final void setMatBene(Integer matricule) {
		this.matBene.setValue(matricule);
	}
	
	
	public final StringProperty nomBeneProperty() {
		return this.nomBene;
	}
	
	public final String getNomBene() {
		return this.nomBeneProperty().get();
	}
	
	public final void setNomBene(String nomBene) {
		this.nomBeneProperty().set(nomBene);
	}
	
	
	public final StringProperty prenomBeneProperty() {
		return prenomBene;
	}
	
	public final String getPrenomBene() {
		return prenomBeneProperty().get();
	}
	
	public final void setPrenomBene(final String prenomBene) {
		this.prenomBeneProperty().set(prenomBene);
	}
	
	
	public final Property<LocalDate> dateNaiBeneProperty() {
		return dateNaiBene;
	}
	
	public final LocalDate getDateNaiBene() {
		return dateNaiBeneProperty().getValue();
	}
	
	public final void setDateNaiBene(LocalDate dateNais) {
		this.dateNaiBeneProperty().setValue(dateNais);
	}
	
	
	public final StringProperty adresseBeneProperty() {
		return adresseBene;
	}
	
	public final String getAdresseBene() {
		return adresseBeneProperty().get();
	}
	
	public final void setAdresseBene(String adresseBene) {
		this.adresseBeneProperty().set(adresseBene);
	}
	
	
	public final StringProperty emailBeneProperty() {
		return emailBene;
	}
	
	public final String getEmailBene() {
		return emailBeneProperty().get();
	}
	
	public final void setEmailBene(String emailBene) {
		this.emailBeneProperty().set(emailBene);
	}
	
	
	public final Property<Poste> posteBeneProperty() {
		return posteBene;
	}
	
	public final projet.data.Poste getPosteBene() {
		return posteBeneProperty().getValue();
	}
	
	public final void setPosteBene(final projet.data.Poste posteBene) {
		this.posteBeneProperty().setValue(posteBene);
	}
	
	
	public final Property<Boolean> permisBeneProperty() {
		return permisBene;
	}
	
	public final Boolean getPermisBene() {
		return permisBene.getValue();
	}
	
	public final void setPermisBene(Boolean permisBene) {
		this.permisBene.setValue(permisBene);
	}
	
	
	public final Property<Boolean> membreClubProperty() {
		return membreClub;
	}

	public final Boolean getMembreClub() {
		return membreClub.getValue();
	}
	
	public final void setMembreClub(Boolean membreClub) {
		this.membreClub.setValue(membreClub);
	}
	
	
//	hashCode() et equals()
	
	
	@Override
	public int hashCode() {
		return Objects.hash(matBene.getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benevole other = (Benevole) obj;
		return Objects.equals(matBene.getValue(), other.matBene.getValue());
	}

	public final Property<Boolean> validerProperty() {
		return this.valider;
	}
	

	public final Boolean getValider() {
		return this.validerProperty().getValue();
	}
	

	public final void setValider(final Boolean valider) {
		this.validerProperty().setValue(valider);
	}
	
	
	
}
