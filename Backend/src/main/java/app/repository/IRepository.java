package app.repository;

public interface IRepository<T> {

    void save(T item);
    void delete(T item);
    /*
    void update(T item);
    void getById(T item);
    void getAll(T item);
    */
}
