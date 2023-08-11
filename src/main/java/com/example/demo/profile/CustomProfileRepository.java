package com.example.demo.profile;

public interface CustomProfileRepository {

    BasicProfileDto getProfileByOwnerId(Long ownerId);

    void updateProfile(Long ownerId, UpdateProfilePhotoRequestDto updateProfilePhotoRequestDto);

}
