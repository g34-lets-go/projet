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


public class DaoBenevole {

	
	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoPoste		daoPoste;
	
	
	public int inserer (Benevole benevole) {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		//Insérer un bénévole

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO benevole ( nom, prenom, adresse, email, id_poste, permis_conduire, date_naissance, membre_ok) VALUES( ?, ?, ?, ?, ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, benevole.getNomBene());
			stmt.setObject( 2, benevole.getPrenomBene());
			stmt.setObject( 3, benevole.getAdresseBene());
			stmt.setObject( 4, benevole.getEmailBene());
			stmt.setObject( 5, benevole.getPosteBene().getId_Poste());
			stmt.setObject( 6, benevole.getPermisBene());
			stmt.setObject( 7, benevole.getDateNaiBene());
			stmt.setObject( 8, benevole.getMembreCLub());
			stmt.executeUpdate();
			
			//Les benevoles doivent être à 2 niveaux 
			//Les internes et les externes
			//Refléchir comment récupérer le type sur l'observablelist
			//Revoir les postes
			
			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			benevole.setMatBene( rs.getObject( 1, Integer.class) );
			
			return benevole.getMatBene();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Modifie les bénévoles
	public void modifier(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le bénévole
			sql = "UPDATE benevole SET nom = ?, prenom = ?, adresse = ?, email = ?, id_poste = ?, permis_conduire = ?, date_naissance = ?, membre_ok = ? WHERE matricule_b =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getNomBene());
			stmt.setObject( 2, benevole.getPrenomBene());
			stmt.setObject( 3, benevole.getAdresseBene());
			stmt.setObject( 4, benevole.getEmailBene());
			stmt.setObject( 5, benevole.getPosteBene());
			stmt.setObject( 6, benevole.getPermisBene());
			stmt.setObject( 7, benevole.getDateNaiBene());
			stmt.setObject( 8, benevole.getMembreCLub());
			stmt.setObject( 9, benevole.getMatBene());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Modifie les telephones
		//daoTelephone.modifierPourPersonne( personne );
	}
	
	// Supprime un bénévole
	public void supprimer(int matricule_b)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		// Supprime les telephones
//		daoTelephone.supprimerPourPersonne( matricule_b );

		try {
			cn = dataSource.getConnection();

			// Supprime le personne
			sql = "DELETE FROM benevole WHERE matricule_b = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, matricule_b );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	//Retrouver un bénévole
	public Benevole retrouver(int matricule_b)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE matricule_b = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, matricule_b);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireBenevole(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerParPoste( int id_poste )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT b.* FROM benevole b" 
				+ " INNER JOIN poste p ON b.id_poste = p.id_poste" 
				+ " WHERE a.id_poste = ?" 
				+ " ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id_poste ); 
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	public List<Benevole> listerParType( int id_poste )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT b.* FROM benevole b" 
				+ " INNER JOIN etre_affecte a ON b.matricule_b = a.matricule" 
				+ " WHERE a.id_poste = ?" 
				+ " ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id_poste ); 
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public int compterParPoste(int id_poste) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM benevole WHERE id_poste = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, id_poste );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
	
	
	// Méthodes auxiliaires
	
		private Benevole construireBenevole( ResultSet rs, boolean flagComplet ) throws SQLException {

			Benevole benevole = new Benevole();
			benevole.setMatBene(rs.getObject( "matricule_b", Integer.class ));
			benevole.setNomBene(rs.getObject( "nom", String.class ));
			benevole.setPrenomBene(rs.getObject( "prenom", String.class ));
			benevole.setAdresseBene(rs.getObject( "adresse", String.class ));
			benevole.setEmailBene(rs.getObject( "email", String.class ));
			benevole.setDateNaiBene(rs.getObject( "date_naissance", LocalDate.class ));
			benevole.setMembreClub(rs.getObject( "membre_ok", Boolean.class ));
			
			if ( flagComplet ) {
				benevole.setPosteBene( daoPoste.retrouver( rs.getObject("id_poste", Integer.class) ) );
			
			}
			
			return benevole;
		}
}
