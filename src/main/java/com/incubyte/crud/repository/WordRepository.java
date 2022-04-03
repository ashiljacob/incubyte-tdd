package com.incubyte.crud.repository;


import com.incubyte.crud.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word,Long> {
}
