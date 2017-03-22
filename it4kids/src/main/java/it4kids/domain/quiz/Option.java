package it4kids.domain.quiz;

/**
 * Clasa asta reprezinta varianta de raspuns creeata de profesor, cand va fi
 * pasata elevului el va vedea doar textul.
 * 
 * @author Catalin
 * 
 */

public class Option extends AbstractModel {

	private String optionText;
	private Boolean correct;

	public String getText() {
		return optionText;
	}

	public void setText(String text) {
		this.optionText = text;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Option [text=" + optionText + ", correct=" + correct + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result + ((optionText == null) ? 0 : optionText.hashCode());
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
		Option other = (Option) obj;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (optionText == null) {
			if (other.optionText != null)
				return false;
		} else if (!optionText.equals(other.optionText))
			return false;
		return true;
	}

}
