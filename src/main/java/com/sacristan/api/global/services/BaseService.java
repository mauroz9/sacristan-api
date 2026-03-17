package com.sacristan.api.global.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.rmi.UnexpectedException;
import java.util.List;

public interface BaseService<T, ID> {

    T save(T entity) throws UnexpectedException;

    T getById(ID id);

    T update(T entity) throws UnexpectedException;

    void delete(T entity) throws UnexpectedException;

    List<T> listAll() throws UnexpectedException;

    Page<T> pageAll(Pageable pageable) throws UnexpectedException;

}
