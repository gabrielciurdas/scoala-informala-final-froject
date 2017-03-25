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
		

	private String textOption;
	private Boolean correct;


	public String getText() {
		return textOption;
	}

	public void setText(String text) {
		this.textOption = text;
	}
	
=======
	private QuizEntry quizEntry;
	private String textOption;
	private Boolean correct;

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
>>>>>>> 313da294fe366a957c0104af1fe310b8197455a8

	public QuizEntry getQuizEntry() {
		return quizEntry;
	}

	public void setQuizEntry(QuizEntry quizEntry) {
		this.quizEntry = quizEntry;
	}

	public String getTextOption() {
		return textOption;
	}

	public void setTextOption(String textOption) {
		this.textOption = textOption;
	}

	@Override
	public String toString() {

		;

		return "Option [text=" + textOption + ", correct=" + correct + "]";

	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result
				+ ((textOption == null) ? 0 : textOption.hashCode());

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

		
		if (text == null) {
			if (other.text != null)

		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (textOption == null) {
			if (other.textOption != null)

				return false;
		} else if (!textOption.equals(other.textOption))
			return false;
		}
		return true;
	}

	public String getCorrect() {
		// TODO Auto-generated method stub
		return null;
	}

}
