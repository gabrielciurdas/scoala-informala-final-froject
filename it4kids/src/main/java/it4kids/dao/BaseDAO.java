package it4kids.dao;

import java.util.Collection;

import it4kids.domain.quiz.AbstractModel;

public interface BaseDAO<T extends AbstractModel> {

	Collection<T> getAll();
	
	T findById(Long id);
	
	T update(T model);
	
	boolean delete(T model);
}

