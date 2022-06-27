/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	private List<Corso> corsi;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private ComboBox<Corso> boxCorsi;

	@FXML
	private Button btnCerca;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaNome;

	@FXML
	private Button btnIscrivi;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtCognome;

	@FXML
	private TextField txtMatricola;

	@FXML
	private TextField txtNome;

	@FXML
	private TextArea txtResult;

	@FXML
	void doCercaCorsi(ActionEvent event) {

		txtResult.clear();

		try {
			int matricola = Integer.parseInt(txtMatricola.getText());

			Studente studente = model.getStudente(matricola);

			if (studente == null) {
				txtResult.setText("Nessun risultato: matricola inesistente");
				return;
			}

			List<Corso> corsi = model.cercaCorsiDatoStudente(studente);

			StringBuilder sb = new StringBuilder();
			for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}

			txtResult.appendText(sb.toString());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		}

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {

		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();

		Corso corso = boxCorsi.getValue();
		if (corso == null) {
			txtResult.setText("Selezionare un corso.");
			return;
		}

		List<Studente> studenti = model.studentiIscrittiAlCorso(corso);

		StringBuilder sb = new StringBuilder();
		for (Studente s : studenti) {
			sb.append(String.format("%-10s ", s.getMatricola()));
			sb.append(String.format("%-20s ", s.getCognome()));
			sb.append(String.format("%-20s ", s.getNome()));
			sb.append(String.format("%-10s ", s.getCds()));
			sb.append("\n");
		}

		txtResult.appendText(sb.toString());

	}

	@FXML
	void doCercaNome(ActionEvent event) {

		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();

		try {
			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);

			if (studente == null) {
				txtResult.setText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		}

	}

	@FXML
	void doIscrivi(ActionEvent event) {

		txtResult.clear();

		try {
			
			if (txtMatricola.getText().isEmpty()) {
				txtResult.setText("Inserire una matricola.");
				return;
			}
			
			Corso corso = boxCorsi.getValue();
			if (corso == null) {
				txtResult.setText("Selezionare un corso.");
				return;
			}
			
			int matricola = Integer.parseInt(txtMatricola.getText());

			Studente studente = model.getStudente(matricola);

			if (studente == null) {
				txtResult.setText("Nessun risultato: matricola inesistente");
				return;
			}
			
			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());
			
			if(model.isStudenteIscrittoACorso(studente, corso)){
				txtResult.appendText("Studente già iscritto a questo corso");
				return;
			} else {
				txtResult.appendText("Studente non iscritto a questo corso");
				return;
			}
				

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		}

	}

	@FXML
	void doReset(ActionEvent event) {
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
		boxCorsi.getSelectionModel().clearSelection();
	}

	private void setComboItems() {
		// ottieni tutti i corsi del model
		corsi = model.getTuttiICorsi();

		// Aggiungi tutti i corsi alla ComboBox.
		Collections.sort(corsi); // per avere maggiore ordine li sorto alfabeticamente
		boxCorsi.getItems().addAll(corsi); // richiama il toString dell'oggetto Corso
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {

		assert boxCorsi != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaIscrittiCorso != null
				: "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		setComboItems();
	}
}