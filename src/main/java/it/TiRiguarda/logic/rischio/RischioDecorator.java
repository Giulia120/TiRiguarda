package it.tiriguarda.logic.rischio;

import it.tiriguarda.domain.LivelloRischio;

public abstract class RischioDecorator implements CalcoloRischio {
	protected CalcoloRischio component;
	
	protected RischioDecorator (CalcoloRischio component) {
		this.component = component;
	}
	
	@Override
	public LivelloRischio calcola() {
		return component.calcola();
	}
}
