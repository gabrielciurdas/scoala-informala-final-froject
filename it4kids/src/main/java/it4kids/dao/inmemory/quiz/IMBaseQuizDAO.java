package it4kids.dao.inmemory.quiz;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import it4kids.dao.BaseDAO;
import it4kids.domain.AbstractModel;

public class IMBaseQuizDAO<T extends AbstractModel> implements BaseDAO<T> {
	private final Map<Long, T> models = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(1);

	@Override
	public Collection<T> getAll() {

		return models.values();
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

	@Override
	public boolean delete(T model) {
		boolean result = models.containsKey(model.getId());

		if (result)
			models.remove(model.getId());
		return result;
	}

}
