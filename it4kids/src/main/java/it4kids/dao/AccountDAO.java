package it4kids.dao;

import java.util.Collection;

import it4kids.domain.login.Account;

/**
 * Created by Gabi on 3/15/2017.
 */
public interface AccountDAO<T extends Account> {

    Collection<T> getAll();

    T add(T t, int id);


    /*T update(T model);

    boolean delete(T model);*/
}
