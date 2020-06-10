package projet.view.equipe;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoEquipe;
import projet.dao.DaoPersonne;
import projet.data.Equipe;
import projet.data.Memo;
import projet.data.Participant;
import projet.data.Personne;
import projet.view.participant.ModelParticipant;
import projet.view.systeme.ModelConfig;


public class ModelEquipe  {
	
	
	// Données observables 
	
	private final ObservableList<Equipe> liste = FXCollections.observableArrayList(); 
	
	private final Equipe	courant = new Equipe();
	
	//private final ObservableList<Personne> personnesPourDialogAjout = FXCollections.observableArrayList();
	
	//private final Property<Image>	schema = new SimpleObjectProperty<>();
	
	//private boolean		flagModifSchema;

	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoEquipe			daoEquipe;
    @Inject
    private ModelParticipant 	modelParticipant;
	
    
	// Initialisations
	
	@PostConstruct
	public void init() {
		//schema.addListener( obs -> flagModifSchema = true );
		//System.out.println("test");
	}
	
	
	// Getters 
	
	public ObservableList<Equipe> getListe() {
		return liste;
	}
	
	public Equipe getCourant() {
		return courant;
	}
	
	/*public ObservableList<Categorie> getCategories() {
		return modelCategorie.getListe();
	}
	
	public ObservableList<Personne> getPersonnesPourDialogAjout() {
		return personnesPourDialogAjout;
	} 
	
	public Property<Image> schemaProperty() {
		return schema;
	}*/
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoEquipe.listerTout() );
 	}
	
	/*public void actualiserListePersonnesPourDialogAjout() {
		personnesPourDialogAjout.setAll( daoPersonne.listerTout() );
		//personnesPourDialogAjout.removeAll( courant.getPersonnes() );
 	}*/


	// Actions
	
	public void preparerAjouter() {
		modelParticipant.actualiserListe();
		mapper.update( courant, new Equipe() );
		//schema.setValue(null);
		//flagModifSchema = false;
	}
	
	public void preparerModifier( Equipe item ) {
		modelParticipant.actualiserListe();
		mapper.update( courant, daoEquipe.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null ||courant.getNom().isEmpty() ) {
			message.append( "\nLe nom de l'equipe ne doit pas être vide." );
		} else  if ( courant.getNom().length()> 50 ) {
			message.append( "\nLe nom d'equipe est trop long : 50 maxi." );
		}
		
		if(courant.getCapitaine().getNom() == null || courant.getCapitaine().getNom().isEmpty()) {
			message.append("\nLe nom ne dois pas etre null");
		}
		
		validerParticipant(courant.getCapitaine());
		validerParticipant(courant.getEquipier());
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoEquipe.inserer( courant ) );
		} else {
			// modficiation
			daoEquipe.modifier( courant );
		}

		/*
		 * if ( flagModifSchema ) { if (schema.getValue() == null) {
		 * getFichierSchemaCourant().delete(); } else { try {
		 * ImageIO.write(SwingFXUtils.fromFXImage(schema.getValue(), null), "JPG",
		 * getFichierSchemaCourant()); } catch (IOException e) { throw new
		 * RuntimeException(e); } } }
		 */		
	}
	
	private void validerParticipant(Participant p) {
		// Vérifie la validité des données
		
				StringBuilder message = new StringBuilder();
				
				//Equipier
				if( p.getNom() == null || p.getNom().isEmpty() ) {
					message.append( "\nLe nom ne doit pas être vide." );
				} else  if ( p.getNom().length()> 25 ) {
					message.append( "\nLe nom est trop long." );
				}

				if( p.getPrenom() == null || p.getPrenom().isEmpty() ) {
					message.append( "\nLe prénom ne doit pas être vide." );
				} else if ( p.getPrenom().length()> 25 ) {
					message.append( "\nLe prénom est trop long." );
				}

				if( p.getTel() == null || p.getEmail() == null  ) {
					message.append( "\nTous les champs doivent être indiquées 2." );
				}
				
				
				if ( message.length() > 0 ) {
					throw new ExceptionValidation( message.toString().substring(1) );
				}
				
	}
	
	public void supprimer( Equipe item ) {
		
		daoEquipe.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
		
		//getFichierSchemaCourant().delete();
	}

	
	/*public void supprimerPersonne( Personne item ) {
		courant.getPersonnes().remove(item);
	}
	
	public void ajouterPersonne( Personne item ) {
		courant.getPersonnes().add(item);
	}*/
	
	
	// Méthodes auxiliaires
	
	/*public File getFichierSchemaCourant() {
		String nomFichier = String.format( "%06d.jpg", courant.getId() );
		File dossierSchemas = modelConfig.getDossierSchemas();
		return new File( dossierSchemas, nomFichier );
	}*/
	
}
