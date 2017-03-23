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
	private String correctOptionText;

	public Option(String optionText, String correctOptionText) {
		this.optionText = optionText;
		this.correctOptionText = correctOptionText;

	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String text) {
		this.optionText = text;
	}

	public String getCorrectOptionText() {
		return correctOptionText;
	}

	public void setCorrectOption(String correct) {
		this.correctOptionText = correct;
	}

	@Override
	public String toString() {
		return "Option [text=" + optionText + ", correct=" + correctOptionText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correctOptionText == null) ? 0 : correctOptionText.hashCode());
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
		if (correctOptionText == null) {
			if (other.correctOptionText != null)
				return false;
		} else if (!correctOptionText.equals(other.correctOptionText))
			return false;
		if (optionText == null) {
			if (other.optionText != null)
				return false;
		} else if (!optionText.equals(other.optionText))
			return false;
		return true;
	}

}
