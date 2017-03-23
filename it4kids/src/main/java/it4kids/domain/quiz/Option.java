package it4kids.domain.quiz;

/**
 * Clasa asta reprezinta varianta de raspuns creeata de profesor, cand va fi
 * pasata elevului el va vedea doar textul.
 * 
 * @author Catalin
 * 
 */

public class Option extends AbstractModel {

<<<<<<< HEAD
	private String text;
		
=======
	private String textOption;
	private Boolean correct;
>>>>>>> origin/Catalin

	public String getText() {
		return textOption;
	}

	public void setText(String text) {
		this.textOption = text;
	}
	

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Option [text=" + text + "]";
=======
		return "Option [text=" + textOption + ", correct=" + correct + "]";
>>>>>>> origin/Catalin
	}

	@Override
	public int hashCode() {
		final int prime = 31;
<<<<<<< HEAD
		int result = 1;		
		result = prime * result + ((text == null) ? 0 : text.hashCode());
=======
		int result = 1;
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result
				+ ((textOption == null) ? 0 : textOption.hashCode());
>>>>>>> origin/Catalin
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
<<<<<<< HEAD
		
		if (text == null) {
			if (other.text != null)
=======
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (textOption == null) {
			if (other.textOption != null)
>>>>>>> origin/Catalin
				return false;
		} else if (!textOption.equals(other.textOption))
			return false;
		return true;
	}

	public String getCorrect() {
		// TODO Auto-generated method stub
		return null;
	}

}
