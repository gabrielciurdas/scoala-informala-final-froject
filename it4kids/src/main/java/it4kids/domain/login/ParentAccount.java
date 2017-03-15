package it4kids.domain.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ParentAccount extends Account {
    private int idChild;

    public ParentAccount(int id, int idRegisteredUser, int idChild) {
        super(id, idRegisteredUser);
        this.idChild = idChild;
    }

    public ParentAccount(int idRegisteredUser, int idChild) {
        super(idRegisteredUser);
        idChild = 0;
    }

    public ParentAccount() {
        super();
        idChild = 0;
    }
    
    public ParentAccount(int idRegisteredUser) {
		super(idRegisteredUser);
	}

    public int getIdChild() {
        return idChild;
    }

    public void setIdChild(int idChild) {
        this.idChild = idChild;
    }
    

}
