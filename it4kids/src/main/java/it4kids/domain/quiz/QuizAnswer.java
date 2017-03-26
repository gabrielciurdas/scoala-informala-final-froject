package it4kids.domain.quiz;

/**
 * Clasa asta este ceea ce o sa vada elevul ca si quiz, si unde o sa raspunda.
 * Ea o sa fie legata de quizul creeat de profesor. Si o sa contina o lista de
 * raspunsuri.
 * 
 * @author Catalin
 * 
 */
public class QuizAnswer {
	private Quiz quiz;
	private UserAnswer userAnswer;
	private double grade;

	public QuizAnswer(Quiz quiz) {
		this.quiz = quiz;

	}

	public String showSummary() {
		return "Number of questions answered :" + userAnswer.getNumberOfQuestions() + "\n Number of correct answers : "
				+ userAnswer.getNumberOfCorrectAnswers();
	}

	public double getGrade() {
		return grade = (userAnswer.getNumberOfCorrectAnswers() * 10) / userAnswer.getNumberOfQuestions();
	}

}
