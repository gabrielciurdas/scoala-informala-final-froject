package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pt quiz, cu o lista de entry uri si un nume
 * 
 * @author Catalin
 * 
 */
public class Quiz extends AbstractModel {

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

}
