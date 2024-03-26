package dao;

import java.util.List;
import java.util.Optional;

/**
 * Базовый интерфейс для всех dao классов
 * @param <K>
 * @param <T>
 */
public interface Dao<K, T> {
    // K - id , T - тип сущности
    List<T> findAll();

    Optional<T> findById(K id);

    T save(T entity);


}

