package it4kids.service.quiz;

import java.util.Collection;

import it4kids.dao.inmemory.quiz.QuizDAO;
import it4kids.domain.quiz.Quiz;

public class QuizService {
	private QuizDAO dao;

	// service 1 , listeaza toate quizurile disponibile.
	public Collection<Quiz> listAll() {
		return dao.getAll();
	}

	// service 2 delete quiz
	public boolean deleteQuiz() {
		return false;
	}

	// service 3 create quiz
	public boolean createQuiz() {
		return false;
	}

	// service 4 edit quiz ? (despre asta nu-s sigur , s-ar putea sa fie de
	// ajuns daca avem edit question pentru fiecare quiz in parte)
	// sau ar putea fi editat numele , timpul , etc.
	public boolean editQuiz() {
		return false;
	}
}
