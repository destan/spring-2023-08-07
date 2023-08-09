package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {

    boolean existsByName(String name);

    Optional<Tag> findByName(String name);
}
