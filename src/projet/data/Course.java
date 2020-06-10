package projet.data;

import java.time.LocalTime;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {

	// Proprietes
	
	private Property<Integer>	id			= new SimpleObjectProperty<>();
	private StringProperty	nom = new SimpleStringProperty();
	private StringProperty	lieuDep	= new SimpleStringProperty();
	private StringProperty	lieuArr	= new SimpleStringProperty();
	private final Property<LocalTime>	heureDep	= new SimpleObjectProperty<>();
	
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
	
	public final StringProperty lieuDepProperty() {
		return this.lieuDep;
	}
	
	public final String getLieuDep() {
		return this.lieuDepProperty().get();
	}
	
	public final void setLieuDep(final String lieuDep) {
		this.lieuDepProperty().set(lieuDep);
	}
	
	public final StringProperty lieuArrProperty() {
		return this.lieuArr;
	}
	
	public final String getLieuArr() {
		return this.lieuArrProperty().get();
	}
	
	public final void setLieuArr(final String lieuArr) {
		this.lieuArrProperty().set(lieuArr);
	}

	public final Property<LocalTime> heureDepProperty() {
		return this.heureDep;
	}
	

	public final LocalTime getHeureDep() {
		return this.heureDepProperty().getValue();
	}
	

	public final void setHeureDep(final LocalTime heureDep) {
		this.heureDepProperty().setValue(heureDep);
	}
	
	
	
	
	
	
	
	
}
