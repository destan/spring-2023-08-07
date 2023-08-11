package com.example.demo.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileRepository profileRepository;

    @GetMapping("users/{userId}/profile/basic")
    ResponseEntity<?> basicProfile(@PathVariable("userId") Long userId) {

        // profileRepository.findById(1L); // CrudRepository
        // profileRepository.getProfileByOwner_Id(1L); // Custom method on ProfileRepository
        // profileRepository.getProfileByOwnerId(1L); // CustomProfileRepository

        return ResponseEntity.ok(profileRepository.getProfileByOwnerId(userId));
    }

    @PatchMapping("users/{userId}/profile")
    ResponseEntity<?> updateProfilePhoto(@PathVariable("userId") Long userId, @RequestBody UpdateProfilePhotoRequestDto requestDto) {
        profileRepository.updateProfile(userId, requestDto);
        return ResponseEntity.noContent().build();
    }

}
