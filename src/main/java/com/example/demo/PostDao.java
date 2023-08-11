package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserWithPostsDto> findUsersPosts(Long userId) {
        return entityManager.createNativeQuery("""
            SELECT u.id AS id,
                   u.username AS username,
                   p.name AS post_name,
                   p.content AS post_content
            FROM app_user u
            JOIN post p ON u.id = p.owner_id
            WHERE u.id = :userId
            """)
                .setParameter("userId", userId)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new UserWithPostsDtoResultTransformer())
                .getResultList();
    }
}
