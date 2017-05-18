package it4kids.dao.inmemory.quiz;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.domain.quiz.Quiz;

@Component
public class IMQuizDAO extends IMBaseQuizDAO<Quiz> implements QuizDAO {

	@Override
	public Collection<Quiz> searchByName(String query) {
		if (query.isEmpty()) {
			return getAll();
		}

		Collection<Quiz> all = new LinkedList<Quiz>(getAll());
		for (Iterator<Quiz> it = all.iterator(); it.hasNext();) {
			Quiz emp = it.next();
			String ss = emp.getName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

}

