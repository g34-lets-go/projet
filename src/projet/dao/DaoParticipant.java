

package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
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
		sql = "INSERT INTO participant ( nom, prenom, date_naiss, telephone, email, adresse, "
				+ "	attestations_ok, frais_paye, repas_supplementaire, id_velo, valider) "
				+ "		VALUES( ?,?,?,?,?,?,?,?,?,?,? ) ";			
		stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
		stmt.setObject( 1, participant.getNom() );
		stmt.setObject( 2, participant.getPrenom() );
		stmt.setObject( 3, participant.getDateN() );
		stmt.setObject( 4, participant.getTel() );
		stmt.setObject( 5, participant.getEmail() );
		stmt.setObject( 6, participant.getAdresse() );
		stmt.setObject( 7, participant.getAttestation() );
		stmt.setObject( 8, participant.getFrais_paye() );
		stmt.setObject( 9, participant.getRepasSup() );
		stmt.setObject( 10, participant.getIdVelo() );
		stmt.setObject( 11, participant.getValider() );
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
	
	// Modifie les bénévoles
	public void modifier(Participant participant)  {
	
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;
	
		try {
			cn = dataSource.getConnection();
	
			// Modifie le bénévole
			sql = "UPDATE participant SET nom = ?, prenom = ?, date_naiss  = ?, telephone = ?, email = ?, adresse = ?, attestations_ok = ?, frais_paye = ?, repas_supplementaire = ?, id_velo = ? WHERE matricule_p =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, participant.getNom());
			stmt.setObject( 2, participant.getPrenom());
			stmt.setObject( 3, participant.getDateN());
			stmt.setObject( 4, participant.getTel());
			stmt.setObject( 5, participant.getEmail());
			stmt.setObject( 6, participant.getAdresse());
			stmt.setObject( 7, participant.getAttestation());
			stmt.setObject( 8, participant.getFrais_paye());
			stmt.setObject( 9, participant.getRepasSup());
			stmt.setObject( 10, participant.getIdVelo());
			stmt.setObject( 11, participant.getId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	// Supprime un participant
	public void supprimer(int matricule_p)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		
		try {
			cn = dataSource.getConnection();

			// Supprime le personne
			sql = "DELETE FROM participant WHERE matricule_p = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, matricule_p );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public Participant retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE matricule_p = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipant(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Participant> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE valider = true ORDER BY nom, prenom";
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
	
	// Lister les participants en attente
		public List<Participant> listerToutAttente()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM participant WHERE valider = false";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Participant> participants = new ArrayList<>();
				while (rs.next()) {
					participants.add( construireParticipant(rs, true) );
				}
				return participants;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
	
	// Rechercher un participant
		public List<Participant> rechercher(String text1, String text2)   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM participant WHERE nom LIKE ? OR nom LIKE ? AND valider = true";
	            stmt = cn.prepareStatement(sql);
	            stmt.setObject( 1, text1+"%");
	            stmt.setObject( 2, text2+"%");
	            rs = stmt.executeQuery();
				
				List<Participant> participants = new ArrayList<>();
				while (rs.next()) {
					participants.add( construireParticipant(rs, true) );
				}
				return participants;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		// Validation	
		public void valider(int matricule_p)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie le bénévole
				sql = "UPDATE participant SET valider = TRUE WHERE matricule_p =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject( 1, matricule_p);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}

		}
	
	private Participant construireParticipant( ResultSet rs, boolean flagComplet ) throws SQLException {

		Participant participant = new Participant();
		participant.setId(rs.getObject( "matricule_p", Integer.class ));
		participant.setNom(rs.getObject( "nom", String.class ));
		participant.setPrenom(rs.getObject( "prenom", String.class ));
		participant.setDateN(rs.getObject( "date_naiss", LocalDate.class ));
		participant.setTel(rs.getObject( "telephone", Integer.class ));
		participant.setAdresse(rs.getObject( "adresse", String.class ));
		participant.setEmail(rs.getObject( "email", String.class ));
		participant.setAttestation(rs.getObject( "attestations_ok", Boolean.class ));
		participant.setRepasSup(rs.getObject( "repas_supplementaire", Integer.class ));
		participant.setIdVelo(rs.getObject( "id_velo", Integer.class ));
		
		
		if ( flagComplet ) {
			//participant.setPosteBene( daoPoste.retrouver( rs.getObject("id_poste", Integer.class) ) );
		}
		
		return participant;
	}
	
}