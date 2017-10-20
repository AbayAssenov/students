package com.github.assenovabay.students.dao;

import java.util.List;

/**
 * @author Abay Assenov
 *         10/16/2017
 */


public interface InterfaceDao<T> {

    void create(T entity);

    void update(T entity);

    List<T> getAll();

    void delete(T user);
}
