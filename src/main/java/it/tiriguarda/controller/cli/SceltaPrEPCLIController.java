package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.domain.TipologiaPrEP;

public class SceltaPrEPCLIController {
	
	public void avviaPrEP(Scanner scanner) {
		boolean fine = false;
		while(!fine) {
			ViewCLI.stampaTitolo("Scelta prep");
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - Daily");
			System.out.println("2 - On Demand");
			System.out.println("3 - Annulla PrEP");
			System.out.println("4 - Registra vecchia PrEP");
			System.out.print("Scegli un'opzione (1-4): ");
			
			String scelta = scanner.nextLine();
			switch(scelta) {
				case "1":
					ConfiguraPrEPCLIController configuraPrEPcontrollerD = new ConfiguraPrEPCLIController();
					configuraPrEPcontrollerD.avviaConfigurazione(TipologiaPrEP.DAILY, scanner);
					break;
				case "2":
					ConfiguraPrEPCLIController configuraPrEPcontrollerOD = new ConfiguraPrEPCLIController();
					configuraPrEPcontrollerOD.avviaConfigurazione(TipologiaPrEP.ON_DEMAND, scanner);
					break;
				case "3":
					AnnullaPrEPCLIController annullaPrEPcontroller = new AnnullaPrEPCLIController();
					boolean tornaMenu = annullaPrEPcontroller.avvioAnnullamento(scanner);
					if(!tornaMenu) {
						return;
					}
					break;
				case "4":
					OldPrEPCLIController configuraOld = new OldPrEPCLIController();
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
