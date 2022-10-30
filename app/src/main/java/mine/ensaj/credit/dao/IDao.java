package mine.ensaj.credit.dao;

import java.util.List;

import mine.ensaj.credit.classes.Credit;

public interface IDao<T> {
    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> findAll();
}