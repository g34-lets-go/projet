package projet.data;


import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Poste  {
	

	// Données observables
	//Revoir horaires
	
	private Property<Integer>	id_poste			= new SimpleObjectProperty<>();
	private StringProperty	nom_poste			= new SimpleStringProperty();
	private StringProperty	description_poste	= new SimpleStringProperty();
	private final Property<LocalTime>	horaire_poste		= new SimpleObjectProperty<>();
	private Property<Integer>	personnel			= new SimpleObjectProperty<>();
	private Property<Integer>	personnel_actuel	= new SimpleObjectProperty<>();
	private final StringProperty	localisation		= new SimpleStringProperty();
	private final StringProperty	equipement			= new SimpleStringProperty();
	private final Property<Integer>	id_course			= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Poste() {
	}

	public Poste( final int id, final String nom, final String description, final LocalTime horaire_poste, final int personnel,final int personnel_actu, final String localisation, final String equipement, final int id_course ) {
		setId_Poste(id);
		setNom_Poste(nom);
		setDescription_poste(description);
		setHoraire_poste(horaire_poste);
		setPersonnel(personnel);
		setPersonnel_actuel(personnel_actu);
		setLocalisation(localisation);
		setEquipement(equipement);
		setId_Course(id_course);
	}
	
	public Poste(Property<Integer> id, StringProperty nom, StringProperty description, Property<Integer> personnel_actuel, Property<Integer> personnel) {
		super();
		this.id_poste=id;
		this.nom_poste=nom;
		this.description_poste=description;
		this.personnel_actuel=personnel_actuel;
		this.personnel=personnel;
	}
	
	
	// Getters et Setters

	public final Property<Integer> id_posteProperty() {
		return this.id_poste;
	}

	public final Integer getId_Poste() {
		return this.id_posteProperty().getValue();
	}

	public final void setId_Poste(final Integer id) {
		this.id_posteProperty().setValue(id);
	}

	
	public final StringProperty nom_posteProperty() {
		return this.nom_poste;
	}

	public final String getNom_Poste() {
		return this.nom_posteProperty().getValue();
	}

	public final void setNom_Poste(final String nom) {
		this.nom_posteProperty().setValue(nom);
	}
	
	
	public final StringProperty description_posteProperty() {
		return this.description_poste;
	}

	public final String getDescription_poste() {
		return this.description_posteProperty().getValue();
	}

	public final void setDescription_poste(final String description) {
		this.description_posteProperty().setValue(description);
	}
	
	
	public final Property<LocalTime> horaire_posteProperty() {
		return horaire_poste;
	}
	
	public final LocalTime getHoraire_poste() {
		return horaire_posteProperty().getValue();
	}
	
	public final void setHoraire_poste(LocalTime horaires) {
		this.horaire_posteProperty().setValue(horaires);
	}
	
	
	public final Property<Integer> personnelProperty() {
		return this.personnel;
	}

	public final Integer getPersonnel() {
		return this.personnelProperty().getValue();
	}

	public final void setPersonnel(final Integer personnel) {
		this.personnelProperty().setValue(personnel);
	}
	
	
	public final StringProperty localisationProperty() {
		return this.localisation;
	}

	public final String getLocalisation() {
		return this.localisationProperty().getValue();
	}

	public final void setLocalisation(final String localisation) {
		this.localisationProperty().setValue(localisation);
	}
	
	
	public final StringProperty equipementProperty() {
		return this.equipement;
	}

	public final String getEquipement() {
		return this.equipementProperty().getValue();
	}

	public final void setEquipement(final String equipement) {
		this.equipementProperty().setValue(equipement);
	}

	
	public final Property<Integer> id_courseProperty() {
		return this.id_course;
	}

	public final Integer getId_Course() {
		return this.id_courseProperty().getValue();
	}

	public final void setId_Course(final Integer id_course) {
		this.id_courseProperty().setValue(id_course);
	}
	
	// toString()
	
	@Override
	public String toString() {
		return getNom_Poste();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id_poste.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poste other = (Poste) obj;
		return Objects.equals(id_poste.getValue(), other.id_poste.getValue() );
	}

	public final Property<Integer> personnel_actuelProperty() {
		return this.personnel_actuel;
	}
	

	public final Integer getPersonnel_actuel() {
		return this.personnel_actuelProperty().getValue();
	}
	

	public final void setPersonnel_actuel(final Integer personnel_actuel) {
		this.personnel_actuelProperty().setValue(personnel_actuel);
	}
	
	
}

