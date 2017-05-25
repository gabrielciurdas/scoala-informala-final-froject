package it4kids.dao.inmemory.quiz;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.domain.quiz.QuizEntry;

@Component
public class IMQuizEntryDAO extends IMBaseQuizDAO<QuizEntry> implements QuizEntryDAO {

	@Override
	public Collection<QuizEntry> searchByName(String query) {
		if (query.isEmpty()) {
			return getAll();
		}

		Collection<QuizEntry> all = new LinkedList<QuizEntry>(getAll());
		for (Iterator<QuizEntry> it = all.iterator(); it.hasNext();) {
			QuizEntry emp = it.next();
			String ss = emp.getText();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}
}
