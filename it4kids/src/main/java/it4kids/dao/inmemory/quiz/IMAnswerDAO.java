package it4kids.dao.inmemory.quiz;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import it4kids.domain.quiz.QuizAnswer;

@Component
public class IMAnswerDAO extends IMBaseAnswerDAO<QuizAnswer> {

	public Collection<QuizAnswer> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<QuizAnswer> all = new LinkedList<QuizAnswer>(getAll());
		for (Iterator<QuizAnswer> it = all.iterator(); it.hasNext();) {
			QuizAnswer qa = it.next();
			String ss = qa.getqEntry() + " " + qa.getOption();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}
}
