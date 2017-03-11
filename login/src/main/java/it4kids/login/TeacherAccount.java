package it4kids.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class TeacherAccount extends Account {

    public TeacherAccount(int id, int idRegisteredUser) {
        super(id, idRegisteredUser);
    }

    public TeacherAccount() {
        super();
    }

    public TeacherAccount(int idRegisteredUser) {
        super(idRegisteredUser);
    }
}
