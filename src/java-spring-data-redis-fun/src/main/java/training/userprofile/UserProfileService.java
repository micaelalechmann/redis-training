package training.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> get(String email) {
        return userProfileRepository.findById(email);
    }

    public Iterable<UserProfile> get() {
        return userProfileRepository.findAll();
    }
}
