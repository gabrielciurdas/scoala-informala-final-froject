package it4kids.domain.quiz;

public class RadioQuestion extends AbstractModel {

	private int correctAnswer;
	private AnswerToQuestion ats;
	private Quiz quiz;
	private int selected;
	private boolean used;

	// un question poate fi instantiat doar daca i se spune care e answer-ul si
	// quizul din care face parte 
	public RadioQuestion(AnswerToQuestion ats, Quiz quiz) {

		this.quiz = quiz;
		this.ats = ats;

	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public AnswerToQuestion getAts() {
		return ats;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public int getSelected() {
		return selected;
	}

	public boolean isUsed() {
		return used;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setAts(AnswerToQuestion ats) {
		this.ats = ats;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
