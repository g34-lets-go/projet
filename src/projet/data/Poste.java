package projet.data;

import java.time.LocalDateTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Poste  {
	

	// Données observables
	//Revoir horaires
	
	private final Property<Integer>	id_poste			= new SimpleObjectProperty<>();
	private final StringProperty	nom_poste			= new SimpleStringProperty();
	private final StringProperty	description_poste	= new SimpleStringProperty();
	private final Property<LocalDateTime>	horaire_poste		= new SimpleObjectProperty<>();
	private final Property<Integer>	personnel			= new SimpleObjectProperty<>();
	private final Property<Integer>	personnel_actuel	= new SimpleObjectProperty<>();
	private final StringProperty	localisation		= new SimpleStringProperty();
	private final StringProperty	equipement			= new SimpleStringProperty();
	private final Property<Integer>	id_course			= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Poste() {
	}

	public Poste( final int id, final String nom, final String description, final int personnel,final int personnel_actu, final String localisation, final String equipement, final int id_course ) {
		setId_Poste(id);
		setNom_Poste(nom);
		setDescription(description);
		setPersonnel(personnel);
		setPersonnel_actu(personnel_actu);
		setLocalisation(localisation);
		setEquipement(equipement);
		setId_Course(id_course);
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
	
	
	public final StringProperty descriptionProperty() {
		return this.description_poste;
	}

	public final String getDescription() {
		return this.descriptionProperty().getValue();
	}

	public final void setDescription(final String description) {
		this.descriptionProperty().setValue(description);
	}
	
	
	public final Property<LocalDateTime> horairesProperty() {
		return horaire_poste;
	}
	
	public final LocalDateTime getHoraires() {
		return horairesProperty().getValue();
	}
	
	public final void setHoraires(LocalDateTime horaires) {
		this.horairesProperty().setValue(horaires);
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

	
	public final Property<Integer> personnel_actuProperty() {
		return this.personnel_actuel;
	}

	public final Integer getPersonnel_actu() {
		return this.personnel_actuProperty().getValue();
	}

	public final void setPersonnel_actu(final Integer personnel) {
		this.personnel_actuProperty().setValue(personnel);
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
	
}

