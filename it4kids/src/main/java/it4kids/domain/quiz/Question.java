package it4kids.domain.quiz;

import java.util.List;

public class Question {

	private List<String> correctAnswers;
	private List<String> incorrectAnswers;

	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public List<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setCorrectAnswers(List<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

}
