package dao.inmemory;

import dao.DAOError;
import dao.ObjectDAO;
import model.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryObjectDAO<E extends Entity> implements ObjectDAO<E> {
    private final Map<Long, E> objects = new HashMap<>();

    @Override
    public List<E> getAll() {
        return objects.values().stream().toList();
    }

    @Override
    public E findById(long id) {
        return objects.get(id);
    }

    @Override
    public E create(E object) throws DAOError {
        if (object.getId() == 0) {
            long maxId = objects.keySet().stream().max(Long::compareTo).orElse(0L);
            object.setId(maxId + 1);
        } else {
            if (objects.containsKey(object.getId())) {
                throw new DAOError("Object with id " + object.getId() + " already exists");
            }
        }
        objects.put(object.getId(), object);
        return object;
    }

    @Override
    public void update(E object) throws DAOError {
        E old = findById(object.getId());
        if (old == null) {
            throw new DAOError("Object not found");
        }
        objects.put(object.getId(), object);
    }

    @Override
    public void delete(long id) throws DAOError {
        E old = findById(id);
        if (old == null) {
            throw new DAOError("Object not found");
        }
        objects.remove(id);
    }
}
