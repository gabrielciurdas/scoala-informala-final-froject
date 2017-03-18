package it4kids.domain.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ChildAccount extends Account {

	private int idParent;

	public ChildAccount(int id, int idRegisteredUser, int idParent) {
		setId(id);
		setIdRegisteredUser(idRegisteredUser);
		this.idParent = idParent;
	}

	public ChildAccount(int idRegisteredUser, int idParent) {
		setIdRegisteredUser(idRegisteredUser);
		setIdParent(idParent);
	}

	public ChildAccount() {
		setId(0);
		setIdRegisteredUser(0);
		idParent = 0;
	}

	public ChildAccount(int idRegisteredUser) {
		setIdRegisteredUser(idRegisteredUser);
	}

	public int getIdParent() {
		return getIdParent();
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}
}
