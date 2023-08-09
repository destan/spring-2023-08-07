package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTags_Name(String tagName, Pageable pageable);

    Page<Post> findByTags_NameAndContentLike(String tagName, String searchTerm, Pageable pageable);
}
