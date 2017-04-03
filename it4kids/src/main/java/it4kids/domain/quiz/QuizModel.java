package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizModel {

	private List<QuizEntry> questions = new ArrayList<>();
	private String name;

	public List<QuizEntry> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuizEntry> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public static void main(String[] args) {
	// Quiz quiz = new Quiz();
	//
	// quiz.setName("Quiz 1");
	// UserAnswer userAnswer = new UserAnswer();
	//
	// List<QuizEntry> questions = new ArrayList<>();
	//
	// QuizEntry quizEntry1 = new QuizEntry();
	// QuizEntry quizEntry2 = new QuizEntry();
	// QuizEntry quizEntry3 = new QuizEntry();
	// /**
	// * Question 1
	// */
	// quizEntry1.setText("Ce culoare are marul?");
	//
	// List<Option> optionsForQuestion1 = new ArrayList<>();
	//
	// Option option1_1 = new Option();
	// option1_1.setCorrect(true);
	// option1_1.setTextOption("Rosu");
	//
	// Option option2_1 = new Option();
	// option2_1.setCorrect(false);
	// option2_1.setTextOption("Albastru");
	//
	// Option option3_1 = new Option();
	// option3_1.setCorrect(false);
	// option3_1.setTextOption("Negru");
	//
	// Option option4_1 = new Option();
	// option4_1.setCorrect(false);
	// option4_1.setTextOption("Gri");
	//
	// optionsForQuestion1.add(option1_1);
	// optionsForQuestion1.add(option2_1);
	// optionsForQuestion1.add(option3_1);
	// optionsForQuestion1.add(option4_1);
	//
	// quizEntry1.setOptions(optionsForQuestion1);
	// questions.add(quizEntry1);
	// /**
	// * Question 2
	// */
	// quizEntry2.setText("Cate mere au culoarea rosie ?");
	// List<Option> optionsForQuestion2 = new ArrayList<>();
	//
	// Option option1_2 = new Option();
	// option1_2.setCorrect(false);
	// option1_2.setTextOption("1");
	//
	// Option option2_2 = new Option();
	// option2_2.setCorrect(false);
	// option2_2.setTextOption("2");
	//
	// Option option3_2 = new Option();
	// option3_2.setCorrect(true);
	// option3_2.setTextOption("3");
	//
	// Option option4_2 = new Option();
	// option4_2.setCorrect(false);
	// option4_2.setTextOption("4");
	//
	// optionsForQuestion1.add(option1_2);
	// optionsForQuestion1.add(option2_2);
	// optionsForQuestion1.add(option3_2);
	// optionsForQuestion1.add(option4_2);
	//
	// quizEntry2.setOptions(optionsForQuestion2);
	// questions.add(quizEntry2);
	//
	// /**
	// * Question 3
	// */
	//
	// quizEntry3.setText("Cate mere sunt in cos ?");
	// List<Option> optionsForQuestion3 = new ArrayList<>();
	//
	// Option option1_3 = new Option();
	// option1_3.setCorrect(false);
	// option1_3.setTextOption("1");
	//
	// Option option2_3 = new Option();
	// option2_3.setCorrect(false);
	// option2_3.setTextOption("2");
	//
	// Option option3_3 = new Option();
	// option3_3.setCorrect(true);
	// option3_3.setTextOption("3");
	//
	// Option option4_3 = new Option();
	// option4_3.setCorrect(false);
	// option4_3.setTextOption("4");
	//
	// optionsForQuestion1.add(option1_3);
	// optionsForQuestion1.add(option2_3);
	// optionsForQuestion1.add(option3_3);
	// optionsForQuestion1.add(option4_3);
	//
	// quizEntry3.setOptions(optionsForQuestion3);
	// questions.add(quizEntry3);
	//
	// quiz.setQuestions(questions);
	//
	// QuizAnswer quizAnswer = new QuizAnswer(quiz);
	//
	// System.out.println(quizAnswer.getGrade());
	// System.out.println(quizAnswer.showSummary());
	//
	// }

}
