package it.tiriguarda.domain;

public class ProtocolloPrEP {
	private Utente utente;
	private final TipologiaPrEP tipoPrEP;
	private boolean statoPrEP;
	
	public ProtocolloPrEP(Utente utente, TipologiaPrEP tipoPrEP, boolean statoPrEP) {
		this.utente = utente;
		this.tipoPrEP = tipoPrEP;
		this.statoPrEP = statoPrEP;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public TipologiaPrEP getTipoPrEP() {
		return tipoPrEP;
	}
	
	public boolean getStatoPrEP() {
		return statoPrEP;
	}
	
}
