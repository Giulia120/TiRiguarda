package it.tiriguarda.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.exception.FileSystemNonRaggiungibileException; 

public class TestDAOFS implements TestDAO {
	private static final Logger logger = Logger.getLogger(TestDAOFS.class.getName());

    private static final String FILE_PATH = "test_records.csv";

    @Override
    public void salvaTest(Test test) {
        String riga = String.format("%s,%s,%s,%s", 
                test.getUtente(),
                test.getidTest(),
                test.getTipo().name(),
                test.getData().toString());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
            bw.write(riga);
            bw.newLine();
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Errore SQL durante la registrazione del test", e);
            throw new FileSystemNonRaggiungibileException("Impossibile salvare il test sul file system locale.");
        }
    }
    
    @Override
    public List<Test> riepilogoTest(String utente, LocalDate data) {
    	List<Test> test = new ArrayList<>();
    	Path path = Paths.get(FILE_PATH);
    	
    	if (!Files.exists(path)) {
            return test;
        }
    	try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue;
                }
                String[] campi = riga.split(",");
                
                if (campi.length >= 4) {
                	
                	String username = campi[0];
                	
                	if(username.equals(utente)){
                		LocalDate dataTest = LocalDate.parse(campi[3]);
                		if (!dataTest.isBefore(data)) {
                            String idTest = campi[1];
                            TipoTest tipo = TipoTest.valueOf(campi[2]);
                            Test t = new Test(username, idTest, tipo, dataTest);
                            test.add(t);
                        }
                	}
                }
            }
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Errore SQL durante il riepilogo (test)", e);
            throw new FileSystemNonRaggiungibileException("Impossibile leggere i test dal file system locale.");
        }
        return test;
    }
}
