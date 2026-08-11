package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;

public class RiepilogoBean {
	private LocalDate data;
	private List<ProtocolloPrEP> prep;
	private List<Rapporto> rapporti;
	private List<Test> test;
	
	
	public LocalDate getData() {
		return data;
	}
	
	private void controllaData(LocalDate data) {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
		if (data.isAfter(LocalDate.now(ZoneId.systemDefault()))) {
            throw new DataFuturaException();
        }
	}
	
	public void setData(LocalDate data) {
		controllaData(data);
		this.data = data;
	}
	public List<ProtocolloPrEP> getPrep() {
		return prep;
	}
	public void setPrep(List<ProtocolloPrEP> prep) {
		this.prep = prep;
	}
	public List<Rapporto> getRapporti() {
		return rapporti;
	}
	public void setRapporti(List<Rapporto> rapporti) {
		this.rapporti = rapporti;
	}
	public List<Test> getTest() {
		return test;
	}
	public void setTest(List<Test> test) {
		this.test = test;
	}
}
