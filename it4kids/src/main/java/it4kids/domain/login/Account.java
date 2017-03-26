package it4kids.domain.login;

import it4kids.domain.AbstractModel;

/**
 * Created by Gabi on 3/10/2017.
 */
public abstract class Account  extends AbstractModel{
    private int idRegisteredUser;


    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }
}
