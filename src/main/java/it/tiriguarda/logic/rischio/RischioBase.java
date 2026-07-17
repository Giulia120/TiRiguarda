package it.tiriguarda.logic.rischio;

import java.util.List;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.TipoRapporto;

public class RischioBase implements CalcoloRischio {
	private List<TipoRapporto> tipoRapporto;
	
	public RischioBase(List<TipoRapporto> tipoRapporto) {
		this.tipoRapporto = tipoRapporto;
	}
	
	@Override
	public LivelloRischio calcola() {
		if (tipoRapporto.contains(TipoRapporto.PENETRATIVO)) {
			return LivelloRischio.ALTO;
		}
		else {
			return LivelloRischio.BASSO;
		}
	}
}
