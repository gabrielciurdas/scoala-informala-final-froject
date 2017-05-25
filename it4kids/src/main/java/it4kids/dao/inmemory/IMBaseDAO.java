package it4kids.dao.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import it4kids.dao.BaseDAO;
import it4kids.domain.AbstractModel;

/**
 * This class performs specific operations on any objects which extends
 * AbstractModel class.
 */
@Component(value="IMBaseDAO")
public abstract class IMBaseDAO<T extends AbstractModel> implements BaseDAO<T> {
	private Map<Long, T> models = new HashMap<Long, T>();
	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

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

		models.put((long) model.getId(), model);
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