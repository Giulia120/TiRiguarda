package it.tiriguarda.controller.graphic;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.controller.app.QuestionarioAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.dto.QuestionBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class QuestionarioGraphicController {
    @FXML private Label testoDomanda; 
    @FXML private RadioButton a;
    @FXML private RadioButton b;
    @FXML private RadioButton c;
    @FXML private ToggleGroup gruppoOpzioni;
    @FXML private Button avantiButton;

    private QuestionarioAppController appController;
    private List<QuestionBean> listaDomande;
    private List<Integer> risposteUtente;
    private int indiceCorrente = 0;

    @FXML
    public void initialize() {
        appController = new QuestionarioAppController();
        listaDomande = appController.getQuestionBeans();
        risposteUtente = new ArrayList<>();
        
        mostraDomandaCorrente();
    }

    private void mostraDomandaCorrente() {
        QuestionBean domandaCorrente = listaDomande.get(indiceCorrente);
        
        testoDomanda.setText(domandaCorrente.getTesto());
        
        a.setText(domandaCorrente.getOpzioni().get(0));
        b.setText(domandaCorrente.getOpzioni().get(1));
        c.setText(domandaCorrente.getOpzioni().get(2));
        
        if (gruppoOpzioni.getSelectedToggle() != null) {
            gruppoOpzioni.getSelectedToggle().setSelected(false);
        }
    }

    @FXML
    public void onAvantiButton() {
        int scelta = -1;
        if (a.isSelected()) scelta = 0;
        else if (b.isSelected()) scelta = 1;
        else if (c.isSelected()) scelta = 2;

        if (scelta == -1) {
            ViewDispatcher.mostraErrore("Devi selezionare una risposta per andare avanti!");
            return;
        }

        risposteUtente.add(scelta);

        if (indiceCorrente < listaDomande.size() - 1) {
            indiceCorrente++;
            mostraDomandaCorrente();
        } else {
            LivelloRischio rischio = appController.valutaRischio(risposteUtente);

            Stage stage = (Stage) avantiButton.getScene().getWindow();
            stage.close();
            ViewDispatcher.mostraRisultatoQuestionario(rischio);
        }
    }
}
