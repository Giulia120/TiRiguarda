package it.tiriguarda.logic.rischio;

import it.tiriguarda.domain.LivelloRischio;

public class PreservativoDecorator extends RischioDecorator {
	public PreservativoDecorator(CalcoloRischio component) {
		super(component);
	}

	@Override
	public LivelloRischio calcola() {
		LivelloRischio rischioAttuale = super.calcola();
		
		if (rischioAttuale == LivelloRischio.NULLO) {
			return rischioAttuale;
		}
		
		return LivelloRischio.NULLO;
	}
}
