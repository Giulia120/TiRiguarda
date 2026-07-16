package it.TiRiguarda.dto;

import it.TiRiguarda.domain.TipologiaPrEP;

public class ProtocolloPrEPBean {
	private TipologiaPrEP tipoPrEP;
	private boolean statoPrEP;
	
	public ProtocolloPrEPBean() {}
	
	public TipologiaPrEP getTipoPrEP() {
		return tipoPrEP;
	}
	
	public boolean getStatoPrEP() {
		return statoPrEP;
	}
	
	public void setTipoPrEP(TipologiaPrEP tipoPrEP) {
		this.tipoPrEP = tipoPrEP;
	}
	
	public void setStatoPrEP(boolean statoPrEP) {
		this.statoPrEP = statoPrEP;
	}
}
