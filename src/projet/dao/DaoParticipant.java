package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
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
}
