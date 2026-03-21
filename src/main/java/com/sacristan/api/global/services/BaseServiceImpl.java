package com.sacristan.api.global.services;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID> &
        JpaSpecificationExecutor<T>> implements BaseService<T, ID> {

    @Autowired
    protected R repository;

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T getById(ID id){
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such entity with ID: " + id)
        );
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> listAll() throws UnexpectedException {
        return repository.findAll();
    }

    @Override
    public Page<T> pageAll(Pageable pageable) throws UnexpectedException {
        return repository.findAll(pageable);
    }

    public Page<T> findByTerm(Pageable pageable, PredicateSpecification<T> q) {
        return repository.findBy(q, p -> p.page(pageable)
        );
    }
}
