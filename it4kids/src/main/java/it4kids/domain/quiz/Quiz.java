package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Flaviu
 *
 */
public class Quiz extends AbstractModel {
	
	List<QuizEntry> entries =  new ArrayList<QuizEntry>();

	// aici ma gandesc la un Quiz , care are ca dependinta un question , iar
	// question are ca dependinta un quiz si un correct answer.
	public Quiz() {

		QuizEntry quizEntry = new QuizEntry.Builder().setQuestion("Cati ani are Basescu?").addOption(1, "55")
				.addOption(2, "62").addOption(3, "49").addOption(4, "58").addResults(1, 2).build();

	}

}
