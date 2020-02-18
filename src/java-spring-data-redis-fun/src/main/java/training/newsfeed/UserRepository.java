package training.newsfeed;

import org.springframework.data.repository.CrudRepository;
import training.userprofile.UserProfile;

public interface UserRepository extends CrudRepository<User, String> {}

