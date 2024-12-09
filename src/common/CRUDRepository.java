package common;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    List<T> getAll();

    int add(T t);

    int update(T t);

    int remove(int id);

    Optional<T> findById(int id);
}
