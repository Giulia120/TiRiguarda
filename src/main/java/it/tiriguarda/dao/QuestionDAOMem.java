package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Question;

public class QuestionDAOMem implements QuestionDAO {
    
    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
        	    "1. Uso del preservativo nei rapporti penetrativi",
        	    List.of("Lo uso sempre.", "Lo uso spesso, ma capita di iniziare senza.", "Non lo uso mai (o quasi mai)."),
        	    List.of(0, 2, 4)
        	));

        	questions.add(new Question(
        	    "2. Tipo di rapporti penetrativi praticati senza preservativo",
        	    List.of("Non ho rapporti penetrativi senza preservativo.", "Rapporti vaginali o anali solo in posizione attiva (insertiva).", "Rapporti anali in posizione passiva (ricettiva)."),
        	    List.of(0, 2, 5)
        	));

        	questions.add(new Question(
        	    "3. Effettuazione del test per HIV",
        	    List.of("Faccio regolarmente il test.", "Faccio il test ma non regolarmente.", "Non ho mai fatto un test per HIV."),
        	    List.of(0, 1, 2)
        	));

        	questions.add(new Question(
        	    "4. Numero di partner sessuali diversi negli ultimi 12 mesi",
        	    List.of("0 o 1 partner.", "Da 2 a 5 partner.", "Più di 5 partner."),
        	    List.of(0, 1, 2)
        	));

        	questions.add(new Question(
        	    "5. Rapporti sessuali occasionali senza protezione",
        	    List.of("Mai avuti.", "Capitato raramente.", "Capita frequentemente con partner di cui non conosco lo stato."),
        	    List.of(0, 3, 5)
        	));

        	questions.add(new Question(
        	    "6. Uso di prevenzione farmacologica (PrEP)",
        	    List.of("Assumo regolarmente la PrEP.", "Non prendo la PrEP, ma uso il preservativo.", "Non prendo la PrEP e non uso costantemente il preservativo."),
        	    List.of(0, 1, 4)
        	));

        	questions.add(new Question(
        	    "7. Partner HIV positivi e terapia (U=U)",
        	    List.of("Non ho partner HIV+ o il partner ha carica virale azzerata (U=U).", "Non conosco lo stato dei miei partner.", "Ho avuto rapporti non protetti con un partner HIV+ non in terapia (carica rilevabile)."),
        	    List.of(0, 2, 5)
        	));

        	questions.add(new Question(
        	    "8. Condivisione di oggetti o sex toys",
        	    List.of("Non uso sex toys o li lavo/proteggo.", "Li sciacquo solo velocemente.", "Condivido sex toys con fluidi freschi senza pulizia."),
        	    List.of(0, 1, 2)
        	));

        	questions.add(new Question(
        	    "9. Presenza di altre Infezioni Sessualmente Trasmissibili (IST)",
        	    List.of("Fatti i controlli, nessuna IST.", "Nessun sintomo, ma non faccio esami da tempo.", "Ho un'IST in corso che ha causato lesioni/ulcere."),
        	    List.of(0, 1, 3)
        	));

        	questions.add(new Question(
        	    "10. Uso di alcol o sostanze (Chemsex)",
        	    List.of("Mai usato sostanze in modo da perdere il controllo.", "A volte l'alcol mi rende meno attento/a.", "Partecipo a sessioni di Chemsex."),
        	    List.of(0, 2, 4)
        	));
        
        return questions;
    }
}
