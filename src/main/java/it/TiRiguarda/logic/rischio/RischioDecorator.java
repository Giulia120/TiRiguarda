package it.TiRiguarda.logic.rischio;

import it.TiRiguarda.domain.LivelloRischio;

public abstract class RischioDecorator implements CalcoloRischio {
	protected CalcoloRischio component;
	
	public RischioDecorator (CalcoloRischio component) {
		this.component = component;
	}
	
	@Override
	public LivelloRischio calcola() {
		return component.calcola();
	}
}
