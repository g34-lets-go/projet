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


public class DaoEquipe {

	
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
		
		daoParticipant.inserer(equipe.getCapitaine());
		daoParticipant.inserer(equipe.getEquipier());

		try {
			cn = dataSource.getConnection();

			// Insère l4equipe
			sql = "INSERT INTO equipe ( nom_equipe, id_capitaine, id_equipier, id_course, numero_dossard ) "
					+ "VALUES ( ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, equipe.getNom() );
			stmt.setInt( 2, equipe.getCapitaine().getId() );
			stmt.setInt( 3, equipe.getEquipier().getId() );
			stmt.setInt( 4, equipe.getIdCourse() );
			stmt.setInt( 5, dernierDossard()+1);
			creerNouveauDossard();
			stmt.executeUpdate();
			
			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			equipe.setId( rs.getObject( 1, Integer.class) );
			return equipe.getId();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void modifier(Equipe equipe)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;
		

		daoParticipant.modifier(equipe.getCapitaine());
		daoParticipant.modifier(equipe.getEquipier());

		try {
			cn = dataSource.getConnection();

			// Modifie une equipe
			sql = "UPDATE equipe SET nom_equipe = ? WHERE id_equipe =  ?";
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

	
	public void supprimer(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime une equipe
			sql = "DELETE FROM equipe WHERE id_equipe = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Equipe retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe WHERE id_equipe = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireEquipe(rs, rs.getInt(3), rs.getInt(4), true );
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

			sql = "SELECT * FROM equipe ORDER BY nom_equipe";
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
		equipe.setId(rs.getObject( "id_equipe", Integer.class ));
		equipe.setNom(rs.getObject( "nom_equipe", String.class ));

		if ( flagComplet ) {
			equipe.setCapitaine(daoParticipant.retrouver(rs.getObject("id_equipe", Integer.class)) );
			equipe.setEquipier(daoParticipant.retrouver(rs.getObject("id_equipe", Integer.class)) );
		}
		
		return equipe;
	}
	
	private Equipe construireEquipe( ResultSet rs, int idCapitain, int idEquipier, boolean flagComplet ) throws SQLException {

		Equipe equipe = new Equipe();
		equipe.setId(rs.getObject( "id_equipe", Integer.class ));
		equipe.setNom(rs.getObject( "nom_equipe", String.class ));
		equipe.setIdCourse(rs.getObject( "id_course", Integer.class ));

		if ( flagComplet ) {
			equipe.setCapitaine(daoParticipant.retrouver(idCapitain) );
			equipe.setEquipier(daoParticipant.retrouver(idEquipier) );
		}
		
		equipe.setRepasSupplementaire(daoParticipant.retrouver(idCapitain).getRepasSup());
		
		return equipe;
	}
	
	public int dernierDossard() {
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		
		try {
			cn = dataSource.getConnection();
            String sql = "SELECT numero_dossard FROM dossard ORDER BY numero_dossard DESC LIMIT 1";
            stmt = cn.prepareStatement( sql );
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public void creerNouveauDossard() {
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();

			// Insère l4equipe
			sql = "INSERT INTO dossard ( numero_dossard ) VALUES ( ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setInt(	1,  dernierDossard()+1);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
}
