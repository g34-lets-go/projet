package projet.data;

import java.util.Objects;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Equipe {


	// Données observables

	private final Property<Integer>		id	 		= new SimpleObjectProperty<>();
	private final StringProperty		nom	 		= new SimpleStringProperty();
	private final Property<Participant>	capt		= new SimpleObjectProperty<>(new Participant());
	private final Property<Participant>	equip		= new SimpleObjectProperty<>(new Participant());
	private final Property<Integer>		idCourse	= new SimpleObjectProperty<>();
	private final Property<Integer>		numDossard	= new SimpleObjectProperty<>();
	
	
	
	
	// Constructeurs
	
	public Equipe() {
	}
	
	public Equipe( String nom, Participant capitaine, Participant equipier, int idCourse, int numDossard) {
		setNom(nom);
		setCapitaine(capitaine);
		setEquipier(equipier);
		setIdCourse(idCourse);
		setNumDossard(numDossard);
				
	}
	
	
	// Getters & setters

	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final java.lang.String getNom() {
		return this.nomProperty().getValue();
	}
	
	public final void setNom(final java.lang.String nom) {
		this.nomProperty().setValue(nom);
	}
	
	

	public final Property<Participant> capitaineProperty() {
		return this.capt;
	}

	public final projet.data.Participant getCapitaine() {
		return this.capitaineProperty().getValue();
	}

	public final void setCapitaine(final projet.data.Participant capitaine) {
		this.capitaineProperty().setValue(capitaine);
	}


	public final Property<Participant> equipierProperty() {
		return this.equip;
	}

	public final projet.data.Participant getEquipier() {
		return this.equipierProperty().getValue();
	}

	public final void setEquipier(final projet.data.Participant equipier) {
		this.equipierProperty().setValue(equipier);
	}

	
	
	
	// toString()
	
	@Override
	public String toString() {
		return getNom();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final Property<Integer> idCourseProperty() {
		return this.idCourse;
	}
	

	public final Integer getIdCourse() {
		return this.idCourseProperty().getValue();
	}
	

	public final void setIdCourse(final Integer idCourse) {
		this.idCourseProperty().setValue(idCourse);
	}
	

	public final Property<Integer> numDossardProperty() {
		return this.numDossard;
	}
	

	public final Integer getNumDossard() {
		return this.numDossardProperty().getValue();
	}
	

	public final void setNumDossard(final Integer numDossard) {
		this.numDossardProperty().setValue(numDossard);
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
