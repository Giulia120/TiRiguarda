package it.tiriguarda.controller.app;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.QuestionDAO;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Question;
import it.tiriguarda.dto.QuestionBean;

public class QuestionarioAppController {
    
    private final QuestionDAO questionDao;

    public QuestionarioAppController() {
        DAOFactory factory = DAOFactoryProvider.getDAOFactory();
        this.questionDao = factory.createQuestionDAO();
    }
    
    public List<QuestionBean> getQuestionBeans() {
        List<Question> questions = questionDao.getAllQuestions();
        List<QuestionBean> beans = new ArrayList<>();
        
        for (Question q : questions) {
            QuestionBean bean = new QuestionBean();
            bean.setTesto(q.getTesto());
            bean.setOpzioni(q.getOpzioni());
            beans.add(bean);
        }
        return beans;
    }

    public LivelloRischio valutaRischio(List<Integer> userChoices) {
    	
    	if ((userChoices.get(0) == 0 && userChoices.get(9) == 0) || userChoices.get(5) == 0) {
            return LivelloRischio.NULLO; 
        }
    	
    	
        List<Question> questions = questionDao.getAllQuestions();
        int score = 0;
 
        for (int i = 0; i < questions.size(); i++) {
            score += questions.get(i).getPesi(userChoices.get(i));
        }
        
        if (score <= 6) {
            return LivelloRischio.NULLO;
        } else if (score <= 17) {
            return LivelloRischio.BASSO;
        } else {
        	return LivelloRischio.ALTO;
        }
    }
}