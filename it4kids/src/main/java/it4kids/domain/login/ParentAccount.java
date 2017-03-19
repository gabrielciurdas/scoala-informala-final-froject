package it4kids.domain.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ParentAccount extends Account {
    private int idChild;

    public ParentAccount(int id, int idRegisteredUser, int idChild) {
    	setId(id);
		setIdRegisteredUser(idRegisteredUser);
		this.idChild = idChild;
    }

    public ParentAccount(int idRegisteredUser, int idChild) {
		setIdRegisteredUser(idRegisteredUser);
		this.idChild = idChild;
    }

    public ParentAccount() {
    	setId(0);
		setIdRegisteredUser(0);
        idChild = 0;
    }
    
    public ParentAccount(int idRegisteredUser) {
		setIdRegisteredUser(idRegisteredUser);
	}

    public int getIdChild() {
        return idChild;
    }

    public void setIdChild(int idChild) {
        this.idChild = idChild;
    }
}
