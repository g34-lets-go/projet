package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Benevole {
	
// Propriétés

	private final Property<Integer>	matBene 	= new SimpleObjectProperty<>();
	private final StringProperty	nomBene 		= new SimpleStringProperty();
	private final StringProperty	prenomBene		= new SimpleStringProperty();
	private final Property<LocalDate>	dateNaiBene	= new SimpleObjectProperty<>();
	private final StringProperty	adresseBene		= new SimpleStringProperty();
	private final StringProperty	emailBene		= new SimpleStringProperty();
	private final StringProperty	posteBene		= new SimpleStringProperty();
	private final Property<Boolean> permisBene 	= new SimpleObjectProperty<>(false);
	private final ObservableList<Integer> typeBene 	= FXCollections.observableArrayList();
	
	
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
	
	public final void setDateNc(LocalDate dateNais) {
		this.dateNaiBeneProperty().setValue(dateNais);
	}
	
	
	public final StringProperty adresseBeneProperty() {
		return adresseBene;
	}
	
	public final String getAdresseBene() {
		return adresseBeneProperty().get();
	}
	
	public final void setAdressec(String adresseBene) {
		this.adresseBeneProperty().set(adresseBene);
	}
	
	
	public final StringProperty emailBeneProperty() {
		return emailBene;
	}
	
	public final String getEmailBene() {
		return emailBeneProperty().get();
	}
	
	public final void setEmailc(String emailBene) {
		this.emailBeneProperty().set(emailBene);
	}
	
	
	public final StringProperty posteBeneProperty() {
		return posteBene;
	}
	
	public final String getPosteBene() {
		return posteBeneProperty().get();
	}
	
	public final void setPosteBene(String posteBene) {
		this.posteBeneProperty().set(posteBene);
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
	
	
	public final StringProperty prenomeProperty() {
		return this.posteBene;
	}
	
	
	public final ObservableList<Integer> getTypeBenevole() {
		return this.typeBene;
	}
	
	public boolean isInRole( Integer typeBenevole ) {
		
		if ( typeBenevole != null ) {
			for ( Integer type : typeBene ) {
				if ( typeBenevole.equals( type ) ) {
					return true;
				}
			}
		}
		return false;
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
	
	
}
