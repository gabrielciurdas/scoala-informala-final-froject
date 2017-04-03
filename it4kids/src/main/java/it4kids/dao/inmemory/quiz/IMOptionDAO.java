package it4kids.dao.inmemory.quiz;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.util.StringUtils;

import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.domain.quiz.Option;

public class IMOptionDAO extends IMBaseQuizDAO<Option> implements OptionDAO {

	@Override
	public Collection<Option> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<Option> all = new LinkedList<Option>(getAll());
		for (Iterator<Option> it = all.iterator(); it.hasNext();) {
			Option emp = it.next();
			String ss = emp.getTextOption() + " " + emp.getCorrect();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}
}
