package app.repository;

public interface IRepository<T> {

    void save(T item);
    void delete(T item);
    /*
    void update(T item);
    void getClientById(T item);
    void getAll(T item);
    */
}
