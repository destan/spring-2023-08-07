package com.example.demo.profile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long>, CustomProfileRepository {

    @Query("""
        SELECT new com.example.demo.profile.BasicProfileDto(p)
            FROM Profile p
            WHERE p.owner.id = :ownerId
    """)
    BasicProfileDto getProfileByOwner_Id(Long ownerId);

}
