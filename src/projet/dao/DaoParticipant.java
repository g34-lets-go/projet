package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Participant;
import projet.data.Participant;

public class DaoParticipant {

	
	@Inject
	private DataSource		dataSource;
	
	public int inserer (Participant participant) {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO participant ( matricule_p, nom, prenom, date_naiss, telphone, email, adresse) VALUES( ?,?,?,?,?,?,? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, participant.getId());
			stmt.setObject( 2, participant.getNomc() );
			stmt.setObject( 3, participant.getPrenomc() );
			stmt.setObject( 4, participant.getDateNc() );
			stmt.setObject( 5, participant.getTelc() );
			stmt.setObject( 6, participant.getEmailc() );
			stmt.setObject( 7, participant.getAdressec() );
			/*stmt.setObject( 7, participant.getLibelle() );
			stmt.setObject( 8, participant.getLibelle() );
			stmt.setObject( 9, participant.getLibelle() );
			stmt.setObject( 10, participant.getLibelle() );
			stmt.setObject( 11, participant.getLibelle() );
			stmt.setObject( 12, participant.getLibelle() );
			stmt.setObject( 13, participant.getLibelle() );*/
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			participant.setId( rs.getObject( 1, Integer.class) );
			return participant.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Modifier les Participants
		public void modifier(Participant participant)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie le bénévole
				sql = "UPDATE participant SET nom = ?, prenom = ?, adresse = ?, email = ?, id_poste = ?, permis_conduite = ?, date_naissance = ?, type_participant = ? WHERE matricule_b =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject( 1, participant.getNomc());
				stmt.setObject( 2, participant.getPrenomc());
				stmt.setObject( 3, participant.getAdressec());
				stmt.setObject( 4, participant.getEmailc());
				stmt.setObject( 5, participant.getPostec());
				stmt.setObject( 6, participant.getPermisBene());
				stmt.setObject( 7, participant.getDateNaiBene());
				participant.setTypeBene(2);
				stmt.setObject( 8, participant.getTypeBene());
				stmt.setObject( 9, participant.getMatBene());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}

			// Modifie les telephones
			//daoTelephone.modifierPourPersonne( personne );
		}
		
		// Modifie les participants
		public void modifier(Participant participant)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie le bénévole
				sql = "UPDATE participant SET nom = ?, prenom = ?, date_naiss = ?, telephone = ?, email = ?, adresse = ?, attestations_ok = ?, frais_paye = ?, rapas_supplementaire = ?, id_velo = ? WHERE matricule_p =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject( 1, participant.getNomc());
				stmt.setObject( 2, participant.getPrenomc());
				stmt.setObject( 3, participant.getDateNc());
				stmt.setObject( 4, participant.getTelc());
				stmt.setObject( 5, participant.getEmailc());
				stmt.setObject( 6, participant.getAdressec());
				stmt.setObject( 7, participant.getAttestationc());
				stmt.setObject( 8, participant.getFrais_payec());			
				stmt.setObject( 9, participant.getRepasSupc());
				stmt.setObject( 10, participant.getIdVeloc());
				stmt.setObject( 11, participant.getIdc());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}

			// Modifie les telephones
			//daoTelephone.modifierPourPersonne( personne );
		}
		
		public List<Participant> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM participant ORDER BY nom, prenom";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Participant> participants = new ArrayList<>();
				while (rs.next()) {
					participants.add( construireParticipant(rs, false) );
				}
				return participants;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		// Méthodes auxiliaires
		
		private Participant construireParticipant( ResultSet rs, boolean flagComplet ) throws SQLException {

			Participant participant = new Participant();
			participant.setIdc(rs.getObject( "matricule_p", Integer.class ));
			participant.setNomc(rs.getObject( "nom", String.class ));
			participant.setPrenomc(rs.getObject( "prenom", String.class ));

			if ( flagComplet ) {
			//	personne.setCategorie( daoCategorie.retrouver( rs.getObject("idcategorie", Integer.class) ) );
			//	personne.getTelephones().addAll( daoTelephone.listerPourPersonne( personne ) );
			}
			
			return participant;
		}
}
