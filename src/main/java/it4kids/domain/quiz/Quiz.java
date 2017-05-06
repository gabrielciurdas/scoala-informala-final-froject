package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import it4kids.domain.AbstractModel;

/**
 * This class represents a quiz with a name and a list of questions.
 * 
 * @author Catalin Jucan
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
}
