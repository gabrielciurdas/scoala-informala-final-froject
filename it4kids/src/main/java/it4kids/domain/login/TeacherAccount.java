package it4kids.domain.login;

/**
 * This class stores the id of a User with teacher role.
 * 
 * Created by Gabriel Ciurdas on 3/10/2017.
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
