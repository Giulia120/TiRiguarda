package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.ZoneId;

import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;

public class TestBean {
	private TipoTest tipo;
	private LocalDate data;

	public TipoTest getTipo() {
		return tipo;
	}
	public void setTipo(TipoTest tipo) throws DatiIncompletiException {
		controllaTipo(tipo);
		this.tipo = tipo;
	}
	
	private void controllaTipo(TipoTest tipo) {
		if (tipo == null) {
			throw new DatiIncompletiException("Devi selezionare il tipo!");
		}
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		controllaData(data);
		this.data = data;
	}
	
	private void controllaData(LocalDate data) {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
		if (data.isAfter(LocalDate.now(ZoneId.systemDefault()))) {
            throw new DataFuturaException();
        }
	}
	
}
