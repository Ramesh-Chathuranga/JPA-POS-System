package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.entity.SuperEntity;


import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    private EntityManager entityManager;
    private Class<T> entity;

    public CrudDAOImpl(){
        entity = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) throws Exception {
        System.out.println("e"+entity.toString());
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManager.merge(entity);
    }

    @Override
    public void delete(ID key) throws Exception {
        entityManager.remove(entityManager.getReference(entity,key));
    }

    @Override
    public Optional<T> find(ID key) throws Exception {
        return Optional.ofNullable(entityManager.find(entity,key));
    }

    @Override
    public Optional<List<T>> findAll() throws Exception {
             return Optional.ofNullable(entityManager.createQuery("FROM " + entity.getName()).getResultList());
    }

}
