package it.tiriguarda.dto;

import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.exception.DatiIncompletiException;

public class UtenteBean {
	private String username;
	private String password;
	private SessoBiologico sessoBiologico;
	private String numeroTelefono;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public SessoBiologico getSessoBiologico() {
		return sessoBiologico;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	public void setUsername(String username) {
		controllaStringa(username, "Devi inserire un username!");
		controllaLunghezzaUsername(username);
		this.username = username;
	}
	
	public void setPassword(String password) {
		controllaStringa(password, "Devi inserire una password!");
		controllaPassword(password);
		this.password = password;
	}
	
	public void setNumeroTelefono(String numeroTelefono) {
		controllaStringa(numeroTelefono, "Devi inserire un numero di telefono!");
		controllaFormatoTelefono(numeroTelefono);
		this.numeroTelefono = numeroTelefono;
	}	

	public void setSessoBiologico(SessoBiologico sessoBiologico) {
		controllaSessoBiologico(sessoBiologico);
		this.sessoBiologico = sessoBiologico;
	}

	private void controllaSessoBiologico(SessoBiologico sessoBiologico) {
		if (sessoBiologico == null) {
			throw new DatiIncompletiException("Devi selezionare il sesso biologico!");
		}
	}
	
	private void controllaStringa(String valore, String messaggioErrore) {
		if (valore == null || valore.isBlank()) {
			throw new DatiIncompletiException(messaggioErrore);
		}
	}
	
	private void controllaLunghezzaUsername(String username) {
		if (username.length() < 3 || username.length() > 30) {
			throw new DatiIncompletiException("L'username deve essere compreso tra 3 e 30 caratteri!");
		}
	}
	
	private void controllaPassword(String password) {
		if (password.length() < 6 || password.length() > 128) {
			throw new DatiIncompletiException("La nuova password deve avere almeno 6 caratteri e massimo 128!");
		}
	}

	private void controllaFormatoTelefono(String numeroTelefono) {
		if (!numeroTelefono.matches("^\\d{9,11}$")) {
			throw new DatiIncompletiException("Il numero di telefono non e' valido (deve contenere solo numeri, tra 9 e 11 cifre)!");
		}
	}
}
