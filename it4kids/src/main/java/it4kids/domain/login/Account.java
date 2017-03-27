package it4kids.domain.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public abstract class Account {
    private Integer id;
    private int idRegisteredUser;

    /*public Account(int id, int idRegisteredUser) {
        this.id = id;
        this.idRegisteredUser = idRegisteredUser;
    }

    public Account(int idRegisteredUser) {
        id = 0;
        this.idRegisteredUser = idRegisteredUser;
    }

  */
    public Account() {
        id = 0;
        idRegisteredUser = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }
}
