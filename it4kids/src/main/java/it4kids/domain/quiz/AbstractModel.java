package it4kids.domain.quiz;

/**
 * Abstract modelul asta e ca si al lui sebi , banuiesc ca si noi avem nevoie de
 * un ID si la quiz , si la question
 * 
 * @author Flaviu
 *
 */
public abstract class AbstractModel {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
