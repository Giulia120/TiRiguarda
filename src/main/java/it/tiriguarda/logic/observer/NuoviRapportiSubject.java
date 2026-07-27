package it.tiriguarda.logic.observer;

public interface NuoviRapportiSubject {
	void attach(NuovoRapportoObserver observer);
    void detach(NuovoRapportoObserver observer);
    void notifyObservers();

}
