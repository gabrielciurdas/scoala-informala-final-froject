package it4kids.domain.quiz;

/**
 * Clasa pt quiz , nu stiu daca field-urile sunt corecte
 * 
 * @author Flaviu
 *
 */
public class Quiz extends AbstractModel {

	private int numberOfQuestions;
	private int wrongAnswers = 0;
	private int totalAnswers = 0;
	private RadioQuestion rq;

	// aici ma gandesc la un Quiz , care are ca dependinta un question , iar
	// question are ca dependinta un quiz si un correct answer.
	public Quiz(RadioQuestion rq) {
		this.rq = rq;
	}

}
