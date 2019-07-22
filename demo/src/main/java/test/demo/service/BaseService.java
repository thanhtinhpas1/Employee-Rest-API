package test.demo.service;

import java.util.List;

public interface BaseService<T> {

    public List<T> getAll();

    public T findOneById(long id);

    public Long createOne(T entity);

    public void updateOne(T entity);

    public void deleteById(long id);
}