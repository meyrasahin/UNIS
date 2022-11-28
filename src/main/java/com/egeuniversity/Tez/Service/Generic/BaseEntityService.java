package com.egeuniversity.Tez.Service.Generic;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import org.hibernate.service.spi.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityService<K extends Serializable & Comparable<K>, E extends BaseEntity<K, ?>> {
    void save(E entity) throws ServiceException;

    void update(E entity) throws ServiceException;

    void create(E entity) throws ServiceException;

    void delete(E entity) throws ServiceException;

    E getById(K id);

    List<E> list();

    Long count();

    void flush();

}
