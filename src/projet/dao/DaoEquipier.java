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
import projet.data.Equipe;


public class DaoEquipier {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoParticipant	daoParticipant;


	
	// Actions

	public int inserer(Equipe equipe)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère l4equipe
			sql = "INSERT INTO equipe ( id, nom_equipe, id_capitaine, id_equipier, id_course, numero_dossard ) "
					+ "VALUES ( ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, equipe.getCapitaine().getId() +"-"+equipe.getEquipier().getId() );
			stmt.setString(	2, equipe.getNom() );
			stmt.setInt( 3, equipe.getCapitaine().getId() );
			stmt.setInt( 4, equipe.getEquipier().getId() );
			stmt.setInt( 5, equipe.getIdCourse());
			stmt.setInt( 6, equipe.getNumDossard());
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return equipe.getNumDossard();
	}

	
	public void modifier(Equipe equipe)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie une equipe
			sql = "UPDATE equipe SET nom = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, equipe.getNom() );
			stmt.setObject( 2, equipe.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

	}

	
	public void supprimer(String id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime une equipe
			sql = "DELETE FROM equipe WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Equipe retrouver(String id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireEquipe(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Equipe> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Equipe> equipes = new ArrayList<>();
			while (rs.next()) {
				equipes.add( construireEquipe(rs, false) );
			}
			return equipes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Equipe> listerPourMemo( int idMemo )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT p.* FROM personne p" 
				+ " INNER JOIN concerner c ON p.idpersonne = c.idpersonne" 
				+ " WHERE c.idmemo = ?" 
				+ " ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idMemo ); 
			rs = stmt.executeQuery();
			
			List<Equipe> personnes = new ArrayList<>();
			while (rs.next()) {
				personnes.add( construireEquipe(rs, false) );
			}
			return personnes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

    
    public int compterPourCategorie(int idCategorie) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM personne WHERE idcategorie = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idCategorie );
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
	
	private Equipe construireEquipe( ResultSet rs, boolean flagComplet ) throws SQLException {

		Equipe equipe = new Equipe();
		equipe.setId(rs.getObject( "id", String.class ));
		equipe.setNom(rs.getObject( "nom", String.class ));

		if ( flagComplet ) {
			equipe.setCapitaine(daoParticipant.retrouver(rs.getObject("id", Integer.class)) );
			equipe.setEquipier(daoParticipant.retrouver(rs.getObject("id", Integer.class)) );
		}
		
		return equipe;
	}
	
}