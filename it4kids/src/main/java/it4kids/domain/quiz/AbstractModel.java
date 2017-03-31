package it4kids.domain.quiz;

import javax.validation.constraints.NotNull;

/**
 * Abstract modelul asta e ca si al lui sebi , banuiesc ca si noi avem nevoie de
 * un ID si la quiz , si la question
 * 
 * @author Flaviu
 *
 */
public abstract class AbstractModel {

	@NotNull
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
