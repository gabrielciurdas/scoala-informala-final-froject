package it4kids.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class Account {
    private int id;
    private int idRegisteredUser;

    public Account(int id, int idRegisteredUser) {
        this.id = id;
        this.idRegisteredUser = idRegisteredUser;
    }

    public Account(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }

  
    public Account() {
        id = 0;
        idRegisteredUser = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }
}
