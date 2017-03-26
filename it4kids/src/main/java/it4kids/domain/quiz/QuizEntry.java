package it4kids.domain.quiz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*public class QuizEntry extends AbstractModel {

	private List<Option> options = new ArrayList<>();
	private String text;
	private Quiz quiz;

	public List<Option> getOptions() {
		return options;
	}*/

public class QuizEntry {

	private Builder builder;

	private QuizEntry(Builder builder) {
		this.builder = builder;
	}

	public String getQuestion() {
		return builder.question;
	}

	public Map<Integer, String> getOptions() {
		return builder.options;
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
