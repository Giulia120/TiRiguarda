package it.TiRiguarda.logic.rischio;

import it.TiRiguarda.domain.LivelloRischio;

public class PrEPDecorator extends RischioDecorator {
	public PrEPDecorator(CalcoloRischio component) {
		super(component);
	}

	@Override
	public LivelloRischio calcola() {
		LivelloRischio rischioAttuale = super.calcola();
		
		if (rischioAttuale == LivelloRischio.ALTO) {
			return LivelloRischio.NULLO;
		}
		
		else if (rischioAttuale == LivelloRischio.BASSO) {
			return LivelloRischio.NULLO;
		}
		
		return rischioAttuale;
	}
}
