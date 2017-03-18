package it4kids.service.quiz;

import java.util.Collection;

import it4kids.dao.inmemory.quiz.RadioQuestionDAO;
import it4kids.domain.quiz.RadioQuestion;

public class QuestionService {

	private RadioQuestionDAO dao;

	// service 1 listAll Questions
	public Collection<RadioQuestion> listAll() {
		return dao.getAll();
	}

	// service 2 delete one question
	public boolean deleteQuestion() {
		return false;
	}

	// service 3 delete all questions
	public boolean deleteAllQuestions() {
		return false;
	}

	// service 4 add new question
	public boolean addQuestion() {
		return false;
	}
	// Completati daca mai aveti idei de alte services , aici sau in clasa
	// QuizService

	// service 4 edit question
	public boolean editQuestion() {
		return false;
	}
}
