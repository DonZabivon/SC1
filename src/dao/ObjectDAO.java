package dao;

import java.util.List;

public interface ObjectDAO<E> {
    List<E> getAll();

    E findById(long id);

    E create(E object) throws DAOError;

    void update(E object) throws DAOError;

    void delete(long id) throws DAOError;
}
