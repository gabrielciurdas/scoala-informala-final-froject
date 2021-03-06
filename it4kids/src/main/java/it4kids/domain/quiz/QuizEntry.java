package it4kids.domain.quiz;

/**
 * Clasa asta reprezinta intrebarea care se pune plus lista de raspunsuri aferente ei.
 * 
 * @author Catalin
 * 
 */
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import it4kids.domain.AbstractModel;

public class QuizEntry extends AbstractModel {

	private List<Option> options = new ArrayList<>();
	@NotNull
	private String text;
	private Long quizId;

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> option) {
		this.options = option;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
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
