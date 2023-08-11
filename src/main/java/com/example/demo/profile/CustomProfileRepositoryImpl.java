package com.example.demo.profile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CustomProfileRepositoryImpl implements CustomProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BasicProfileDto getProfileByOwnerId(Long ownerId) {
        final TypedQuery<BasicProfileDto> query = entityManager.createQuery("""
                SELECT new com.example.demo.profile.BasicProfileDto(p)
                    FROM Profile p
                    WHERE p.owner.id = :ownerId
                """, BasicProfileDto.class)
                .setParameter("ownerId", ownerId);

        final BasicProfileDto result = query.getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public void updateProfile(Long ownerId, UpdateProfilePhotoRequestDto updateProfilePhotoRequestDto) {
        final Query query = entityManager.createNativeQuery("""
                            update profile 
                                set photo = :photoUrl
                                where id in
                                (select profile_id from app_user where id = :ownerId)
                        """, BasicProfileDto.class)
                .setParameter("photoUrl", updateProfilePhotoRequestDto.photoUrl())
                .setParameter("ownerId", ownerId);

        final int updatedRowCount = query.executeUpdate();
        log.debug("Affected row count after update: {}", updatedRowCount);
    }

}
