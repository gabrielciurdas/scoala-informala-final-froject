package it4kids.domain.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswer extends AbstractModel {
	private Quiz quiz;
	private QuizEntry qEntry;
	private Option option;
	private List<Option> options = new ArrayList<>();

	public long getQuizID(long id) {
		return quiz.getId();
	}

	public Option getOption() {
		return option;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public QuizEntry getqEntry() {
		return qEntry;
	}

}
