package it4kids.domain.quiz;

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
public class QuizAnswer {

	Quiz q = new Quiz();
	private final List<Answer> answear = new ArrayList<>();

}
