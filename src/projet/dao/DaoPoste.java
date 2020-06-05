package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Poste;


public class DaoPoste {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO poste ( nom_poste, description_poste, horaires_poste, personnel_poste, localisation_poste, Equipement_necessaire, id_course, nb_personnel ) VALUES( ?, ?, ?, ?, ?, ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, poste.getNom_Poste() );
			stmt.setObject( 2, poste.getDescription_Poste() );
			stmt.setObject( 3, poste.getHoraire_poste() );
			stmt.setObject( 4, poste.getPersonnel() );
			stmt.setObject( 5, poste.getLocalisation() );
			stmt.setObject( 6, poste.getEquipement() );
			stmt.setObject( 7, poste.getId_Course() );
			stmt.setObject( 8, poste.getPersonnel_actuel() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			poste.setId_Poste( rs.getObject( 1, Integer.class) );
			return poste.getId_Poste();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE poste SET nom_poste = ?, description_poste = ?, horaires_poste = ?, personnel_poste = ?, localisation_poste = ?, Equipement_necessaire = ?, id_course = ?, nb_personnel = ? WHERE id_poste =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, poste.getNom_Poste() );
			stmt.setObject( 2, poste.getDescription_Poste() );
			stmt.setObject( 3, poste.getHoraire_poste() );
			stmt.setObject( 4, poste.getPersonnel() );
			stmt.setObject( 5, poste.getLocalisation() );
			stmt.setObject( 6, poste.getEquipement() );
			stmt.setObject( 7, poste.getId_Course() );
			stmt.setObject( 8, poste.getPersonnel_actuel() );
			stmt.setObject( 9, poste.getId_Poste() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void modifierNB( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE poste SET nom_poste = ( SELECT COUNT(b.matricule_b) FROM poste po INNER JOIN benevole b ON po.id_poste = b.id_poste WHERE matricule_b = new.matricule_b)";
			stmt = cn.prepareStatement( sql );
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int id_poste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM poste WHERE id_poste = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, id_poste );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Poste retrouver( int id_poste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste WHERE id_poste = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, id_poste);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construirePoste( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	
	public Poste retrouverPoste( int id_poste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT nom FROM poste WHERE id_poste = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, id_poste);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construirePoste( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	


	public List<Poste> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste ORDER BY nom_poste";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( construirePoste( rs ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Rechercher un poste
	public List<Poste> rechercher(String text1, String text2)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM poste WHERE nom LIKE ? OR nom LIKE ?";
	        stmt = cn.prepareStatement(sql);
	        stmt.setObject( 1, text1+"%");
	        stmt.setObject( 2, text2+"%");
	        rs = stmt.executeQuery();
				
			List<Poste> postes = new ArrayList<>();
			while (rs.next()) {
				postes.add( construirePoste(rs) );
			}
			return postes;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		

	
	// Méthodes auxiliaires
	
	private Poste construirePoste( ResultSet rs ) throws SQLException {
		Poste poste = new Poste();
		poste.setId_Poste( rs.getObject( "id_poste", Integer.class ) );
		poste.setNom_Poste( rs.getObject( "nom_poste", String.class ) );
		poste.setDescription_Poste( rs.getObject( "description_poste", String.class ) );
		poste.setHoraire_poste( rs.getObject( "horaires_poste", LocalTime.class ) );
		poste.setLocalisation( rs.getObject( "localisation_poste", String.class ) );
		poste.setEquipement( rs.getObject( "Equipement_necessaire", String.class ) );
		poste.setPersonnel( rs.getObject( "personnel_poste", Integer.class ) );
		poste.setPersonnel_actuel( rs.getObject( "nb_personnel", Integer.class ) );
		poste.setId_Course( rs.getObject( "id_course", Integer.class ) );
		
		return poste;
	}

}
