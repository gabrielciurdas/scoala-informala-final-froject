package it4kids.dao;

import it4kids.domain.login.Account;

import java.util.Collection;

/**
 * Created by Gabi on 3/15/2017.
 */
public interface AccountDAO<T extends Account> {

	Collection<T> getAll();

    T add(T model, Integer id);
    
    T findById(Long id);

    T update(T model);

   /* boolean delete(T model);*/
}
