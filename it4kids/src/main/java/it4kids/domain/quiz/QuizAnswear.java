package it4kids.domain.quiz;

import it4kids.dao.quiz.QuizDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa asta este ceea ce o sa vada elevul ca si quiz, si unde o sa raspunda.
 * Ea o sa fie legata de quizul creeat de profesor. Si o sa contina o lista de
 * raspunsuri.
 * 
 * @author Catalin
 * 
 */
public class QuizAnswear {

	Quiz q = new Quiz();
	private final List<Answear> answear = new ArrayList<>();
	private final QuizDAO dao = dao.findById();

}
