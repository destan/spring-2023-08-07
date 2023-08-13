package com.example.demo;

import com.example.demo.aspect.Measured;
import com.example.demo.profile.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    User register(User user) {

        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

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

    Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
