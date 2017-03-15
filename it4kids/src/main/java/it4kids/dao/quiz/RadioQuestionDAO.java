package it4kids.dao.quiz;

import java.util.Collection;

import it4kids.domain.quiz.RadioQuestion;


public interface RadioQuestionDAO extends BaseDAO<RadioQuestion>{
	
	Collection<RadioQuestion> searchByName(String query);

}
