package it4kids.service.quiz;

import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.domain.quiz.Option;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class OptionService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuizService.class);

	@Autowired
	private OptionDAO dao;

	public Collection<Option> listAll() {
		return dao.getAll();
	}

	public Collection<Option> search(String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		Option emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	public Option get(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return dao.findById(id);

	}

	public void save(Option option)
			throws it4kids.service.ValidationException {
		LOGGER.debug("Saving: " + option);
		validate(option);

		dao.update(option);
	}

	private void validate(Option option)
			throws it4kids.service.ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(option.getClass())) {
			errors.add("Name is Empty");
		}

		if (option.getText() == null) {
			errors.add("There are no questions");
		}

		// if (option.getCorrect() == null) {
		// errors.add("There are no answears");
		// }

		if (!errors.isEmpty()) {
			throw new it4kids.service.ValidationException(
					errors.toArray(new String[] {}));
		}
	}

	public OptionDAO getOptionDao() {
		return dao;
	}

	public void setOptionDao(OptionDAO dao) {
		this.dao = dao;
	}

}
