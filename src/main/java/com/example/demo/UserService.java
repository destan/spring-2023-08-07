package com.example.demo;

import com.example.demo.aspect.Measured;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    User register(User user) {

        userRepository.save(user);

        return user;
    }

    @Measured
    List<User> listAll() {

        return userRepository.findAllWithPosts();
    }

    Profile replaceProfile(Profile profile) {
        return null;
    }

    //@Transactional
    Profile updateProfile(Long userId, Profile profile) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            // throw new IllegalArgumentException();
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException("User with id %s does not exist!".formatted(userId));
        }

        final User user = userOptional.get();

        Profile persistedProfile = user.getProfile();

        if (profile.getAge() != null) {
            persistedProfile.setAge(profile.getAge());
        }

        if (profile.getName() != null) {
            persistedProfile.setName(profile.getName());
        }

        if (profile.getPhoto() != null) {
            persistedProfile.setPhoto(profile.getPhoto());
        }

        userRepository.save(user);

        return user.getProfile();
    }

    void delete(Long id) {
        userRepository.deleteById(id);
    }

    Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
