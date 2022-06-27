package it.polito.tdp.lab04.model;

import java.util.Objects;

public class Studente {

	private int matricola;
	private String nome;
	private String cognome;
	private String cds;

	public Studente(int matricola, String nome, String cognome, String cds) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}
	
	public Studente(int matricola) {
		this.matricola = matricola;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCds() {
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cds, cognome, matricola, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		return Objects.equals(cds, other.cds) && Objects.equals(cognome, other.cognome) && matricola == other.matricola
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", cds=" + cds + "]";
	}

}
