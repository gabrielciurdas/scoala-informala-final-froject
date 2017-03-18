package it4kids.domain.login;

/**
 * Created by Gabi on 3/10/2017.
 */
public class TeacherAccount extends Account {

    public TeacherAccount(int id, int idRegisteredUser) {
    	setId(id);
		setIdRegisteredUser(idRegisteredUser);
    }

    public TeacherAccount() {
    	setId(0);
		setIdRegisteredUser(0);
    }

    public TeacherAccount(int idRegisteredUser) {
		setIdRegisteredUser(idRegisteredUser);
    }
}
