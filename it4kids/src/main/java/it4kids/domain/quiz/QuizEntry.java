package it4kids.domain.quiz;

<<<<<<< HEAD
/**
 * Clasa asta reprezinta intrebarea care se pune plus lista de raspunsuri aferente ei.
 * 
 * @author Catalin
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class QuizEntry extends AbstractModel {

	private List<Option> options = new ArrayList<>();
	private String text;
	private Long quizId;

	public List<Option> getOptions() {
		return options;
	}
=======
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
>>>>>>> origin/it4kids

public class QuizEntry {

	private Builder builder;

	private QuizEntry(Builder builder) {
		this.builder = builder;
	}

<<<<<<< HEAD
	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
=======
	public String getQuestion() {
		return builder.question;
	}

	public Map<Integer, String> getOptions() {
		return builder.options;
>>>>>>> origin/it4kids
	}

	public Set<Integer> getResults() {
		return builder.results;
	}

	public static class Builder {

		private String question;
		private Map<Integer, String> options;
		private Set<Integer> results;

		public Builder() {
			this.options = new HashMap<>();
			this.results = new HashSet<>();
		}

		public Builder setQuestion(String question) {
			this.question = question;
			return this;
		}

		public Builder addOption(int option, String description) {
			options.put(option, description);
			return this;
		}

		public Builder addResults(int... results) {
			for (int result : results) {
				this.results.add(result);
			}
			return this;
		}

		public QuizEntry build() {
			return new QuizEntry(this);
		}

	}

}
