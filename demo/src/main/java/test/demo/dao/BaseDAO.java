package test.demo.dao;

import java.util.List;

public interface BaseDAO<T> {
    public List<T> getAll();

    public T getOne(long id);

    public Long saveOne(T entity);

    public void updateOne(T entity);

    public void deleteOne(long id);

}