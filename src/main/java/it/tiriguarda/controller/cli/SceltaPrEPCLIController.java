package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.domain.TipologiaPrEP;

public class SceltaPrEPCLIController {
	private ConfiguraPrEPCLIController configuraPrEPcontrollerD = new ConfiguraPrEPCLIController();
	private ConfiguraPrEPCLIController configuraPrEPcontrollerOD = new ConfiguraPrEPCLIController();
	private AnnullaPrEPCLIController annullaPrEPcontroller = new AnnullaPrEPCLIController();
	private OldPrEPCLIController configuraOld = new OldPrEPCLIController();
	
	public void avviaPrEP(Scanner scanner) {
		boolean fine = false;
		while(!fine) {
			ViewCLI.stampaTitolo("Scelta prep");
			ViewCLI.mostraMenu(
			"Daily",
			"On Demand",
			"Annulla PrEP",
			"Registra vecchia PrEP");
			
			String scelta = scanner.nextLine();
			switch(scelta) {
				case "1":
					configuraPrEPcontrollerD.avviaConfigurazione(TipologiaPrEP.DAILY, scanner);
					break;
				case "2":
					configuraPrEPcontrollerOD.avviaConfigurazione(TipologiaPrEP.ON_DEMAND, scanner);
					break;
				case "3":
					boolean tornaMenu = annullaPrEPcontroller.avvioAnnullamento(scanner);
					if(!tornaMenu) {
						return;
					}
					break;
				case "4":
					configuraOld.avviaConfigurazione(scanner);
					break;
				case "q":
					return;
				default:
					ViewCLI.stampaInvalido();
			}
		}
	}
}
