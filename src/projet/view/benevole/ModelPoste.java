package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoPoste;
import projet.data.Poste;


public class ModelPoste  {
	
	
	// Données observables 
	
	private final ObservableList<Poste> liste = FXCollections.observableArrayList(); 
	
	private final Poste	courant = new Poste();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoPoste		daoPoste;
    @Inject
    private DaoBenevole		daoBenevole;
    @Inject
//    private DaoMemo			daoMemo;
	
	
	// Getters 
	
	public ObservableList<Poste> getListe() {
		return liste;
	}
	
	public Poste getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPoste.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Poste() );
	}
	
	public void preparerModifier( Poste item ) {
		mapper.update( courant, daoPoste.retrouver( item.getId_Poste() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom_Poste() == null || courant.getNom_Poste().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNom_Poste().length()> 25 ) {
			message.append( "\nLe nom est trop long : 25 maxi." );
		}
		
		if( courant.getDescription() == null || courant.getDescription().isEmpty() ) {
			message.append( "\nLa description ne doit pas être vide." );
		}
		
		if( courant.getPersonnel() == null) {
			message.append( "\nLe nombre de personne nécessaire ne doit pas être vide." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId_Poste() == null ) {
			// Insertion
			courant.setId_Poste( daoPoste.inserer( courant ) );
		} else {
			// modficiation
			daoPoste.modifier( courant );
		}
	}
	
	
	public void supprimer( Poste item ) {
		
		// Vérifie l'abence de benevoles rattachées à la catégorie
		if ( daoBenevole.compterParPoste( item.getId_Poste() ) != 0 ) {
			throw new ExceptionValidation( "Des bénévoles sont rattachées à ce poste.." ) ;
		}
/*		
		// Vérifie l'abence de mémos rattaches à la catégorie
		if ( daoMemo.compterPourCategorie( item.getId() ) != 0 ) {
			throw new ExceptionValidation( "Des mémos sont rattachés à cette catégorie.." ) ;
		}
*/		
		daoPoste.supprimer( item.getId_Poste() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
