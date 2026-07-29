package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ViewCLI {
	    
	    private static final String SEPARATORE = "========================================";

	    public static void stampaSeparatore() {
	        System.out.println(SEPARATORE);
	    }

	    public static void stampaTitolo(String titolo) {
	        System.out.println(SEPARATORE);
	        System.out.println("        " + titolo.toUpperCase() + "        ");
	        System.out.println(SEPARATORE);
	        System.out.println("(Digita 'q' in qualsiasi momento per annullare e tornare al menu)\n");
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
	    
	    public static void stampaInvalido() {
	    	System.out.println("[ERRORE] Opzione non valida, riprova!");
	    }
	    
	    public static void stampaSuccesso() {
	    	System.out.println("\n****************************************");
			System.out.println("*  È un successo!   *");
			System.out.println("****************************************");
	    }
	}
