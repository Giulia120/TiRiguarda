package it.tiriguarda.controller.graphic;

import java.io.File;
import java.util.List;

import it.tiriguarda.controller.app.RiepilogoAppController;
import it.tiriguarda.dto.EventoRiepilogo;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.util.GeneratorePDFUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;

public class RiepilogoGraphicController {
	
	@FXML
	private DatePicker dataRiepilogoPicker;
	
	@FXML
	private Button generaButton;
	
	@FXML
	private Button backButton;
	
	
	@FXML
	public void onGeneraButton() {
	    try {
	        RiepilogoBean bean = new RiepilogoBean();
	        bean.setData(dataRiepilogoPicker.getValue());
	        
	        RiepilogoAppController controller = new RiepilogoAppController();
	        List<EventoRiepilogo> eventi = controller.getReportRiepilogo(bean);

	        if (eventi.isEmpty()) {
	            ViewDispatcher.mostraErrore("Nessun dato trovato.");
	            return;
	        }

	        FileChooser fileChooser = new FileChooser();
	        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
	        fileChooser.setInitialFileName("Report_Riepilogo.pdf");
	        File file = fileChooser.showSaveDialog(null);

	        if (file != null) {
	            GeneratorePDFUtil.genera(file, eventi);
	            ViewDispatcher.mostraSuccesso("Report salvato correttamente!");
	        }

	    } catch (Exception e) {
	        ViewDispatcher.mostraErrore("Errore nella generazione del PDF: " + e.getMessage());
	    }
	}
		
	@FXML
	public void onBackButton(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
	
}
