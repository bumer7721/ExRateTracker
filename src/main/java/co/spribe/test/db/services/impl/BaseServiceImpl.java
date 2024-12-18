package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.BaseEntity;
import co.spribe.test.db.repositiries.BaseRepository;
import co.spribe.test.db.services.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseRepository<T> getRepository();

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
}
