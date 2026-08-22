package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ViewCLI {
	
	private ViewCLI() {
        // Costruttore privato per nascondere quello pubblico di default
    }
	
	    private static final String SEPARATORE = "\n========================================";
	    
	    public static void mostraMenu(String... opzioni) {
	    	if (opzioni == null || opzioni.length == 0) {
	            return;
	        }
	    	for (int i = 0; i < opzioni.length; i++) {
	            System.out.println((i + 1) + " - " + opzioni[i]);
	        }	        
	        System.out.print("Scegli un'opzione (1-" + opzioni.length + " o q): ");
	    }
	    
	    public static void stampaMessaggio(String mes) {
	    	System.out.print("\n" + mes);
	    }
	
	    public static LocalDate leggiData(Scanner scanner) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			while (true) {
				System.out.print("Inserisci la data (gg/mm/aaaa): ");
				String input = scanner.nextLine();
				
				if (input.equalsIgnoreCase("q")) {
					return null;
				}
				
				try {
					return LocalDate.parse(input, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Formato o data non valido! Usa gg/mm/aaaa.");
				}
			}
		}
	
	    public static void stampaSeparatore() {
	        System.out.println(SEPARATORE);
	    }
	
	    public static void stampaTitolo(String titolo) {
	        System.out.println(SEPARATORE);
	        System.out.println("        " + titolo.toUpperCase() + "        ");
	        System.out.println(SEPARATORE);
	        System.out.println("(Digita 'q' in qualsiasi momento per annullare e tornare indietro)\n");
	    }
	
	    public static void stampaInvalido() {
	    	System.out.println("[ERRORE] Opzione non valida, riprova!");
	    }
	
	    public static void stampaSuccesso(Scanner scanner) {
	    	System.out.println("\n****************************************");
			System.out.println("*            E' un successo!             *");
			System.out.println("****************************************");
			System.out.println("Premi INVIO tornare indietro");
			scanner.nextLine();
	    }
	
	    public static void stampaErrore (String messaggio) {
	    	System.out.println("\n[ERRORE DI VALIDAZIONE]: " + messaggio);
	    }
	
	    public static void stampaErroreSistema(String messaggio) {
	    	System.out.println("\n[ERRORE DI SISTEMA]: ");
	    	System.out.println(messaggio);
	        System.out.println("Verrai reindirizzato alla schermata di Login...");
	    }  
	
	    public static void stampaErroreCriticoEChiudi(String messaggio) {
	        System.out.println(SEPARATORE);
	        System.out.println("[ERRORE CRITICO DI CONNESSIONE]");
	        System.out.println(messaggio);
	        System.out.println("L'applicazione verra' chiusa per evitare perdite di dati.");
	        System.out.println(SEPARATORE);
	        System.exit(1);
	    }
	}
