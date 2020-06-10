package projet.view.participant;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoParticipant;
import projet.dao.DaoPersonne;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Participant;
import projet.data.Personne;
import projet.data.Telephone;


public class ModelParticipant {
	
	
	// Données observables 
	
	private final static ObservableList<Participant> liste = FXCollections.observableArrayList();
	
	private final Participant	courantCapitain = new Participant();
	private final Participant	courantEquipier = new Participant();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoParticipant			daoParticipant;
	
	
	// Getters 
    
    public static ObservableList<Participant> getListe() {
		return liste;
	}
    
	public Participant getCourantCapitain() {
		return courantCapitain;
	}
	
	public Participant getCourantEquipier() {
		return courantEquipier;
	}

	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoParticipant.listerTout() );
	}
	
	public void actualiserListeAttente() {
		liste.setAll( daoParticipant.listerToutAttente() );
	}

	
	
	// Actions	
	public void preparerAjouter() {
//		modelCategorie.actualiserListe();
		mapper.update( courantCapitain, new Participant() );
		mapper.update( courantEquipier, new Participant() );
	}
	

	public void preparerModifier( Participant itemC, Participant itemE  ) {
//		modelCategorie.actualiserListe();
		mapper.update( courantCapitain, daoParticipant.retrouver( itemC.getId() ) );
		mapper.update( courantEquipier, daoParticipant.retrouver( itemE.getId() ) );
		
	}
	
	public void rechercher(String text1) {
		liste.setAll( daoParticipant.rechercher(text1.toLowerCase(), text1.toUpperCase()) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		//Capitain
		/*if( courantCapitain.getNom() == null || courantCapitain.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courantCapitain.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courantCapitain.getPrenom() == null || courantCapitain.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courantCapitain.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if( courantCapitain.getTel() == null || courantCapitain.getEmail() == null   ) {
			message.append( "\nTous les champs doivent être indiquées 1." );
		}*/
		
		//Equipier
		if( courantEquipier.getNom() == null || courantEquipier.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courantEquipier.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courantEquipier.getPrenom() == null || courantEquipier.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courantEquipier.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if( courantEquipier.getTel() == null || courantEquipier.getEmail() == null  ) {
			message.append( "\nTous les champs doivent être indiquées 2." );
		}
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		
		if ( courantCapitain.getId() == null || courantEquipier.getId() == null) {
			// Insertion
			courantCapitain.setId( daoParticipant.inserer( courantCapitain ) );
			courantEquipier.setId( daoParticipant.inserer( courantEquipier ) );
		} else {
			// modficiation
			daoParticipant.modifier( courantCapitain );
			daoParticipant.modifier( courantEquipier );
		}
	}
	
	public void validation (Participant item) {
		daoParticipant.valider(item.getId());
	}
	

	/*public void supprimer( Personne item ) {
		daoPersonne.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	

	public void ajouterTelephone() {
		courant.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
		courant.getTelephones().remove( telephone );
	}*/
	
}
