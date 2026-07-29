package it.tiriguarda.dto;

import java.util.List;

public class QuestionBean {
	private String testo;
	private List<String> opzioni;
 
 public String getTesto() { 
	 return testo;
	 }
 
 public void setTesto(String testo) { 
	 this.testo = testo;
	 }
 
 public List<String> getOpzioni() { 
	 return opzioni;
	 }
 
 public void setOpzioni(List<String> opzioni) { 
	 this.opzioni = opzioni;
	 }
}
