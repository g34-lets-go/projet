package projet.view.benevole;

import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.data.Benevole;


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
    //private ModelCategorie 		modelCategorie;
	
	
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

	
	// Actions
	
	public void preparerAjouter() {
//		modelCategorie.actualiserListe();
		mapper.update( courant, new Benevole() );
	}
	

	public void preparerModifier( Benevole item ) {
//		modelCategorie.actualiserListe();
		mapper.update( courant, daoBenevole.retrouver( item.getMatBene() ) );
		
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
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
	/*
	public void ajouterTelephone() {
//		courant.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
//		courant.getTelephones().remove( telephone );
	}
	*/
}
