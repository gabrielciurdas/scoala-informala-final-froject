package it4kids.dao.inmemory.login;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import it4kids.dao.AccountDAO;
import it4kids.domain.login.Account;

public class IMAccountDAO<T extends Account> implements AccountDAO<T> {
	
	protected Map<Integer, T> models = new HashMap<Integer, T>();
	protected static AtomicInteger ID = new AtomicInteger((int) System.currentTimeMillis());

	@Override
	public Collection<T> getAll() {
		return models.values();
	}

	@Override
	public T add(T model, Integer id) {
		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}
		models.put(model.getId(), model);
		return model;
	}
	
	@Override
	public T findById(Long id) {

		return models.get(id);
	}
	
	@Override
	public T update(T model) {
		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}

		models.put(model.getId(), model);
		return model;
	}
}
