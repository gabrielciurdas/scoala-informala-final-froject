package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Clasa pt quiz, cu o lista de entry uri si un nume
 * 
 * @author Catalin
 * 
 */
public class Quiz extends AbstractModel {

	private List<QuizEntry> questions = new ArrayList<>();
	@NotNull
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

	// aici ma gandesc la un Quiz , care are ca dependinta un question , iar
	// question are ca dependinta un quiz si un correct answer.
	// public Quiz() {
	//
	// QuizEntry quizEntry = new QuizEntryBuilder()
	// .setQuestion("Cati ani are Basescu?").addOption(1, "55")
	// .addOption(2, "62").addOption(3, "49").addOption(4, "58")
	// .build();
	//
	// }

}
