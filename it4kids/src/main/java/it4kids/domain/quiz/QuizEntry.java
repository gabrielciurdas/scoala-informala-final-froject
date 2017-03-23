package it4kids.domain.quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clasa asta reprezinta intrebarea care se pune plus lista de raspunsuri aferente ei.
 * 
 * @author Catalin
 * 
 */

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class QuizEntry extends AbstractModel {

	@NotNull
	@NotEmpty
	private String text;

	private Map<String, List<Option>> questions = new HashMap<String, List<Option>>();
	private List<Option> options;

	private Option option;

	public void createQuestion() {
		QuizEntry quizEntry = new QuizEntry();
		options.add(new Option(option.getOptionText(), option.getCorrectOptionText()));
		questions.put(quizEntry.getText(), options);

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizEntry other = (QuizEntry) obj;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}

// <#-- Set Options: <input name = "option"
// value="${quizEntry.fields[option]!''}" />
// Set Correct Answer: <input name = "correctAnswer"
// value="${quizEntry.correctAnswer!''}" /> -->
