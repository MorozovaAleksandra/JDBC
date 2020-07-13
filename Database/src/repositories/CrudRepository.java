package repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id);
    void save(T entity) throws SQLException;
    void update(T entity);

}
