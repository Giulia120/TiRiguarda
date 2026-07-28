package it.tiriguarda.manager;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.SmsDAO;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;

public class SmsScheduler {
    private static final Logger logger = Logger.getLogger(SmsScheduler.class.getName());

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final SmsManager smsManager = new SmsManager();

    public void avviaScheduler() {

        scheduler.scheduleAtFixedRate(() -> {
            try {
                DAOFactory factory = DAOFactoryProvider.getDAOFactory();
                SmsDAO smsDao = factory.createSmsDAO();
                UtenteDAO utenteDao = factory.createUtenteDAO();
                
                List<Sms> smsDaInviare = smsDao.recuperaSmsDaInviare();
                
                if (smsDaInviare != null && !smsDaInviare.isEmpty()) {
                    logger.info("Trovati " + smsDaInviare.size() + " SMS da inviare.");
                    
                    for (Sms sms : smsDaInviare) {
                        try {
                        	String numeroDestinatario = utenteDao.recuperaNumeroTelefono(sms.getUtente());
                            smsManager.inviaSms(sms, numeroDestinatario);
                            if (sms.getTipo() == TipoSms.PREP_DAILY) {
                            	smsDao.aggiornaData(sms);
                            }
                            smsDao.aggiornaStato(sms, StatoSms.INVIATO);
                        } catch (Exception e) {
                            logger.severe("Errore durante l'invio dell'SMS: " + e.getMessage());
                            smsDao.aggiornaStato(sms, StatoSms.ERRORE);
                        }
                    }
                }
            } catch (Exception e) {
                logger.severe("Errore nel task di background degli SMS: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public void arrestaScheduler() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}