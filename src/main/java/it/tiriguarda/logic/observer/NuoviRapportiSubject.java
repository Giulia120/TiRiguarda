package it.tiriguarda.logic.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class NuoviRapportiSubject {
private final List<NuovoRapportoObserver> observers = new ArrayList<>();
	
	public void attach(NuovoRapportoObserver observer) {
		this.observers.add(observer);
	}
	
	 public void detach(NuovoRapportoObserver observer) {
	        observers.remove(observer);
	    }

	 protected void notifyObservers() {
	        for (NuovoRapportoObserver obs : observers) {
	            obs.update();
	        }
	    }
}

