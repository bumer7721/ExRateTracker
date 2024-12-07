package co.spribe.test.db.services;

import co.spribe.test.db.entities.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    List<T> findAll();

}
