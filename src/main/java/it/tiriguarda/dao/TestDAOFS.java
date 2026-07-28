package it.tiriguarda.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import it.tiriguarda.domain.Test;
import it.tiriguarda.exception.FileSystemNonRaggiungibileException; 

public class TestDAOFS implements TestDAO {

    private static final String FILE_PATH = "test_records.csv";

    @Override
    public void salvaTest(Test test) {
        String riga = String.format("%s,%s,%s,%s", 
                test.getUtente(),
                test.getidTest(),
                test.getTipo().name(),
                test.getData().toString());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(riga);
            bw.newLine();
        } catch (IOException e) {
            throw new FileSystemNonRaggiungibileException("Impossibile salvare il test sul file system locale.");
        }
    }
}
