package it.tiriguarda.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import it.tiriguarda.domain.Sms;

public class SmsManager {

    private static final String FILE_SMS = "sms_inviati.txt";

    public void inviaSms(Sms sms, String numeroDestinatario) {
    	String testoMessaggio = sms.getTesto();
    	LocalDateTime oraAttuale = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timestamp = oraAttuale.format(formatter);
    	

        String logSms = String.format("[%s] INVIATO A: %s\nMESSAGGIO: %s", 
                                      timestamp, 
                                      numeroDestinatario, 
                                      testoMessaggio);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_SMS, true))) {
            bw.write(logSms);
            bw.newLine();
            bw.write("--------------------------------------------------");
            bw.newLine();
        } catch (IOException e) {
        	System.out.println("Errore durante la simulazione dell'invio SMS ");
            //throw new SmsNonInviatoException("Errore durante la simulazione dell'invio SMS ");
        }
    }
}