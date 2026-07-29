package it.tiriguarda.dto;

import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;

public class RiepilogoBean {
	private List<ProtocolloPrEP> prep;
	private List<Rapporto> rapporti;
	private List<Test> test;
	
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
