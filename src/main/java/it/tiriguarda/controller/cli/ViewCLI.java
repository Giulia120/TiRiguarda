package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ViewCLI {
	
	private ViewCLI() {
        // Costruttore privato per nascondere quello pubblico di default
    }
	@SuppressWarnings("java:S106")
	    private static final String SEPARATORE = "========================================";
	@SuppressWarnings("java:S106")
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
	@SuppressWarnings("java:S106")
	    public static void stampaSeparatore() {
	        System.out.println(SEPARATORE);
	    }
	@SuppressWarnings("java:S106")
	    public static void stampaTitolo(String titolo) {
	        System.out.println("\n" + SEPARATORE);
	        System.out.println("        " + titolo.toUpperCase() + "        ");
	        System.out.println(SEPARATORE);
	        System.out.println("(Digita 'q' in qualsiasi momento per annullare e tornare al menu)\n");
	    }
	@SuppressWarnings("java:S106")
	    public static void stampaInvalido() {
	    	System.out.println("[ERRORE] Opzione non valida, riprova!");
	    }
	@SuppressWarnings("java:S106")
	    public static void stampaSuccesso(Scanner scanner) {
	    	System.out.println("\n****************************************");
			System.out.println("*            È un successo!             *");
			System.out.println("****************************************");
			System.out.println("Premi INVIO tornare al menu principale");
			scanner.nextLine();
	    }
	@SuppressWarnings("java:S106")
	    public static void stampaErrore (String messaggio) {
	    	System.out.println("\n[ERRORE DI VALIDAZIONE]: " + messaggio);
	    }
	@SuppressWarnings("java:S106")
	    public static void stampaErroreSistema(String messaggio) {
	    	System.out.println("\n[ERRORE DI SISTEMA]: ");
	    	System.out.println(messaggio);
	        System.out.println("Verrai reindirizzato alla schermata di Login...");
	    }  
	@SuppressWarnings("java:S106")
	    public static void stampaErroreCriticoEChiudi(String messaggio) {
	        System.out.println("\n" + SEPARATORE);
	        System.out.println("[ERRORE CRITICO DI CONNESSIONE]");
	        System.out.println(messaggio);
	        System.out.println("L'applicazione verra' chiusa per evitare perdite di dati.");
	        System.out.println(SEPARATORE);
	        System.exit(1);
	    }
	}
