package com.guaxinim.library.persistence;

public interface Neo4jService<T> {
    Iterable<T> findAll();
    T find(String property, Long id);
    void delete(Long id);
    void createOrUpdate(T object);
}