package it.tiriguarda.manager;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.SmsDAO;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.exception.SmsNonInviatoException;

public class SmsScheduler {
	private static SmsScheduler instance;
    private static final Logger logger = Logger.getLogger(SmsScheduler.class.getName());

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final SmsManager smsManager = new SmsManager();
    private boolean isAvviato = false;    
    
    private SmsScheduler() {}

    public static synchronized SmsScheduler getInstance() {
        if (instance == null) {
            instance = new SmsScheduler();
        }
        return instance;
    }

    public void avviaScheduler() {
		if (isAvviato) {
			return;
		}

		scheduler.scheduleAtFixedRate(this::eseguiTaskSms, 0, 1, TimeUnit.MINUTES);
		isAvviato = true;
		logger.info("Scheduler SMS avviato in background.");
	}

	private void eseguiTaskSms() {
		try {
			DAOFactory factory = DAOFactoryProvider.getDAOFactory();
			SmsDAO smsDao = factory.createSmsDAO();
			UtenteDAO utenteDao = factory.createUtenteDAO();
			
			List<Sms> smsDaInviare = smsDao.recuperaSmsDaInviare();
			
			if (smsDaInviare != null && !smsDaInviare.isEmpty()) {
				logger.info(()-> "Trovati " +  smsDaInviare.size() + " SMS da inviare.");
				
				for (Sms sms : smsDaInviare) {
					elaboraSingoloSms(sms, smsDao, utenteDao);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Errore nel task di background degli SMS", e);
		}
	}


	private void elaboraSingoloSms(Sms sms, SmsDAO smsDao, UtenteDAO utenteDao) {
		try {
			String numeroDestinatario = utenteDao.recuperaNumeroTelefono(sms.getUtente());
			smsManager.inviaSms(sms, numeroDestinatario);
			
			if (sms.getTipo() == TipoSms.PREP_DAILY) {
				smsDao.aggiornaData(sms);
			} else {
				smsDao.aggiornaStato(sms, StatoSms.INVIATO);
				logger.info("SMS inviato con successo a: " + sms.getUtente());
			}
		} catch (SmsNonInviatoException e) {
			logger.log(Level.SEVERE, "Errore durante l''invio dell''SMS", e.getMessage());
			}
			
			if (sms.getTipo() == TipoSms.PREP_DAILY) {
				smsDao.aggiornaData(sms); 
			} else {
				smsDao.aggiornaStato(sms, StatoSms.ERRORE);
			}
		}

    public void arrestaScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            isAvviato = false;
            logger.info("Scheduler SMS fermato.");
        }
    }
}