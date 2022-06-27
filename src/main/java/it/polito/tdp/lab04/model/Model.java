package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.studenteDAO = new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDAO.getTuttiICorsi();
	}

	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}
	
	public List<Studente> studentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> cercaCorsiDatoStudente(Studente studente){
		return studenteDAO.getCorsiFromStudente(studente);
	} 
	
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {
		return studenteDAO.isStudenteIscrittoACorso(studente,corso);
	}
	
}
