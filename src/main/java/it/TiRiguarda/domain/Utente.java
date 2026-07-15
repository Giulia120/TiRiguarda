package it.TiRiguarda.domain;

import java.util.List;

public class Utente {
	private final String idUtente;
	private final String username;
	private final String numeroTelefono;
	private List<Rapporto> rapporti;
	private ProtocolloPrEP protocolloAttivo;	
}
