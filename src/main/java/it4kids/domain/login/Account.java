package it4kids.domain.login;

import it4kids.domain.AbstractModel;

/**
 * This class stores the id of a registered user.
 * 
 * Created by Gabriel Ciurdas on 3/10/2017.
 */
public class Account  extends AbstractModel{
    private int idRegisteredUser;


    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRegisteredUser;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (idRegisteredUser != other.idRegisteredUser)
			return false;
		return true;
	}
}
