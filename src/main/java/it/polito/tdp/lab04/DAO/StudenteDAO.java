package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		
		final String sql = "SELECT * FROM studente WHERE matricola=?"; 
		Studente studente = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				studente = new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
			}
		
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}	
			
		return studente;
	}

	public List<Corso> getCorsiFromStudente(Studente studente) {
		final String sql = "SELECT * FROM iscrizione JOIN corso AS C ON iscrizione.codins = C.codins "+
				"WHERE iscrizione.matricola=?;";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"),
						rs.getInt("pd"));
				corsi.add(c);
			}

			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {
		
		final String sql = "SELECT * FROM iscrizione where codins=? and matricola=?";
		boolean returnValue = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();

			if (rs.next())
				returnValue = true;

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return returnValue;
	}

}
