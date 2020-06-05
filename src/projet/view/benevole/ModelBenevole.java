package projet.view.benevole;

import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.data.Benevole;
import projet.data.Poste;


public class ModelBenevole {
	
	
	// Données observables 
	
	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	private final Property<Integer> type = new SimpleObjectProperty<>(1);
	private final ObservableList<Integer> listeType = FXCollections.observableArrayList();
	private final Benevole	courant = new Benevole();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoBenevole			daoBenevole;
	@Inject
    private ModelPoste 		modelPoste;
	
	
	// Getters 
	
	public ObservableList<Benevole> getListe() {
		return liste;
	}
	
	public ObservableList<Integer> getListeType() {
		return listeType;
	}
	
	public Property<Integer> typeProperty() {
		return type;
	}
	
	public Benevole getCourant() {
		return courant;
	}
	
	public ObservableList<Poste> getPostes() {
		return modelPoste.getListe();
	}
	
/*
	//@PostConstruct
	public void init() {
		listeType.addAll(1,2);
		changerType();
	}
	
	public void changerType() {
		if(courant.getTypeBene()<1 || courant.getTypeBene()>2) {
			
		}
	}
*/	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoBenevole.listerTout() );
	}
	
	public void actualiserListeAttente() {
		liste.setAll( daoBenevole.listerToutAttente() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		modelPoste.actualiserListe();
		mapper.update( courant, new Benevole() );
	}
	

	public void preparerModifier( Benevole item ) {
		modelPoste.actualiserListe();
		mapper.update( courant, daoBenevole.retrouver( item.getMatBene() ) );
		
	}
	
	public void rechercher(String text1) {
		liste.setAll( daoBenevole.rechercher(text1.toLowerCase(), text1.toUpperCase()) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getNomBene() == null || courant.getNomBene().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNomBene().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courant.getPrenomBene() == null || courant.getPrenomBene().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courant.getPrenomBene().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if( courant.getPermisBene() == null || courant.getAdresseBene() == null || courant.getEmailBene() == null || courant.getPosteBene() == null  ) {
			message.append( "\nTous les champs doivent être indiquées." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		
		if ( courant.getMatBene() == null ) {
			// Insertion
			courant.setMatBene( daoBenevole.inserer( courant ) );
		} else {
			// modficiation
			daoBenevole.modifier( courant );
		}
	}
	

	public void supprimer( Benevole item ) {
		daoBenevole.supprimer( item.getMatBene() );
		//mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
	public Benevole rechercher(int matricule_b ) {
		return daoBenevole.retrouver(matricule_b);
		
	}
	
	public void validation (Benevole item) {
		daoBenevole.valider(item.getMatBene());
	}
	

}
