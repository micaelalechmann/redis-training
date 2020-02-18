package training.newsfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.userprofile.UserProfile;
import training.userprofile.UserProfileRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationService publicationService;

   public void follow(String username, String followedUsername) {
       Optional<User> user = userRepository.findById(username);
       Optional<User> followedUser = userRepository.findById(followedUsername);

       if(!user.isPresent() || !followedUser.isPresent()) {
           //retornar excessao
           return;
       }

       if(!followedUser.get().getFollowers().contains(username) &&
               !user.get().getFollowing().contains(followedUsername)) {
           user.get().getFollowing().add(followedUsername);
           followedUser.get().getFollowers().add(username);

           publicationService.addPublicationsOnFeed(username, followedUser.get().getPublications());

           userRepository.save(user.get());
           userRepository.save(followedUser.get());
       }
   }

   public void unfollow(String username, String unfollowedUsername) {
       Optional<User> user = userRepository.findById(username);
       Optional<User> unfollowedUser = userRepository.findById(unfollowedUsername);

       if(!user.isPresent() || !unfollowedUser.isPresent()) {
           //retornar exceçao
           return;
       }

       if(unfollowedUser.get().getFollowers().contains(username) &&
               user.get().getFollowing().contains(unfollowedUsername)) {
           user.get().getFollowing().remove(unfollowedUsername);
           unfollowedUser.get().getFollowers().remove(username);

           publicationService.deletePublicationsFromFeed(username, unfollowedUser.get().getPublications());

           userRepository.save(user.get());
           userRepository.save(unfollowedUser.get());
       }
   }

    public void delete(String id) {

    }

   public Optional<User> get(String username) {
       return userRepository.findById(username);
   }

   public User save(User user) {
       return userRepository.save(user);
   }

}
