package it4kids.domain.quiz;

public class UserAnswer {

	private QuizEntry qEntry;

	private int selectedAnswer;

	private boolean answerIsCorrect = true;
	private int numberOfQuestions;
	private int numberOfCorrectAnswers;

	public boolean checkIfAnswerIsCorrect() {

		if (qEntry.getExpected() == selectedAnswer) {
			numberOfQuestions++;
			numberOfCorrectAnswers++;
			return answerIsCorrect;
		} else
			numberOfQuestions++;
		return answerIsCorrect = false;

	}

	public QuizEntry getqEntry() {
		return qEntry;
	}

	public int getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(int selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public boolean isAnswerIsCorrect() {
		return answerIsCorrect;
	}

	public void setqEntry(QuizEntry qEntry) {
		this.qEntry = qEntry;
	}

	public void setAnswerIsCorrect(boolean answerIsCorrect) {
		this.answerIsCorrect = answerIsCorrect;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public int getNumberOfCorrectAnswers() {
		return numberOfCorrectAnswers;
	}

	public void setAnswerCorrect(boolean answerIsCorrect) {
		this.answerIsCorrect = answerIsCorrect;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}

}
