package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //@Query(value = "SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.posts LEFT JOIN FETCH u.profile")
    List<User> findAllWithPosts();

    @Query(value = "SELECT * FROM APP_USER WHERE password IS NULL", nativeQuery = true)
    List<User> nativeSqlExample();

}
