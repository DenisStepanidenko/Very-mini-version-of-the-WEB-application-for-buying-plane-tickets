package mapper;


/**
 * Базовый интерфейс для всех mapper, F - изначальный объект, T - объект, который должны получить на выходе
 */
public interface Mapper<F, T> {
    T mapFrom(F object);
}
