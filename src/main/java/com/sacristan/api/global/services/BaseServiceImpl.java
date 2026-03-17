package com.sacristan.api.global.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    protected final R repository;


    @Override
    public T save(T entity) throws UnexpectedException {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            throw new UnexpectedException("Failed to create entity: "+ex.getMessage());
        }
    }

    @Override
    public T getById(ID id){
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such entity with ID: " + id)
        );
    }

    @Override
    public T update(T entity) throws UnexpectedException {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            throw new UnexpectedException("Failed to update entity: " + ex.getMessage());
        }
    }

    @Override
    public void delete(T entity) throws UnexpectedException {
        try {
            repository.delete(entity);
        }  catch (Exception ex) {
            throw new UnexpectedException("Failed to delete entity: " + ex.getMessage());
        }
    }

    @Override
    public List<T> listAll() throws UnexpectedException {
        try {
            return repository.findAll();
        }  catch (Exception ex) {
            throw new UnexpectedException("Failed to list entities: " + ex.getMessage());
        }
    }

    @Override
    public Page<T> pageAll(Pageable pageable) throws UnexpectedException {
        try {
            return repository.findAll(pageable);
        }   catch (Exception ex) {
            throw new UnexpectedException("Failed to page entities: " + ex.getMessage());
        }
    }
}
