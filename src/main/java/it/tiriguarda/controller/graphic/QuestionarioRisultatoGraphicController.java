package it.tiriguarda.controller.graphic;

import it.tiriguarda.domain.LivelloRischio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class QuestionarioRisultatoGraphicController {

    @FXML private Circle coloreRischio;
    @FXML private Label livelloRischio;
    @FXML private Label testoRisultato;
    @FXML private Button menuPrincipaleButton;

    public void inizializza(LivelloRischio rischio, int score) {
        
        switch (rischio) {
            case NULLO:
                livelloRischio.setText("Rischio Basso o Nullo");
                livelloRischio.setTextFill(Color.GREEN);
                coloreRischio.setFill(Color.GREEN);
                testoRisultato.setText("I tuoi comportamenti indicano un'eccellente attenzione alla prevenzione. Il rischio biologico attuale è minimo. Continua così e ricordati di inserire il test HIV nella tua normale routine di controlli sanitari in caso di nuovi partner futuri.");
                break;
                
            case BASSO:
                livelloRischio.setText("Rischio Moderato");
                livelloRischio.setTextFill(Color.ORANGE);
                coloreRischio.setFill(Color.ORANGE);
                testoRisultato.setText("Ci sono alcune abitudini o situazioni episodiche in cui potresti esporti accidentalmente al virus. Potrebbe essere utile stabilizzare l'uso del preservativo dall'inizio del rapporto o valutare l'inizio della PrEP con un infettivologo se l'uso del profilattico risulta difficile da mantenere.");
                break;
                
            case ALTO:
                livelloRischio.setText("Rischio Alto");
                livelloRischio.setTextFill(Color.RED);
                coloreRischio.setFill(Color.RED);
                testoRisultato.setText("I tuoi comportamenti ti espongono a una concreta e frequente probabilità di contrarre l'HIV. Si raccomanda vivamente di effettuare un test HIV al più presto (se non eseguito di recente) e di prenotare un colloquio clinico per accedere alla PrEP o ad altre strategie mirate di riduzione del danno.");
                break;
        }
    }

    @FXML
    public void onMenuPrincipale(ActionEvent event) {
        ViewDispatcher.mostraMenuPrincipale();
    }
}