package training.newsfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String,String> redis;

    public Publication publish(String username, Publication publication) {
        User user = userService.get(username).orElse(null);
        if(null == user) {
            //jogar exceçao
            return null;
        }

        publication.setId(getNextPublicationId());
        publication.setOwner(username);
        publicationRepository.save(publication);
        user.getPublications().add(publication.getId());
        userService.save(user);
        sendToFollowers(user.getFollowers(), publication);
        return publication;
    }

    public Iterable<Publication> getNewsFeed(String username) {
        Set<String> publicationIds = redis.opsForZSet().reverseRangeByScore("news_feed_" + username, 0, 25);
        return publicationRepository.findAllById(publicationIds);
    }

    public Optional<Publication> get(String id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if(publication.isPresent()) {
            Optional<User> user = userService.get(publication.get().getOwner());
            if (user.isPresent()) {
                user.get().getFollowers().forEach(follower -> {
                    redis.opsForZSet().incrementScore("news_feed_" + follower, publication.get().getId(), 1L);
                });
            }
        }
        return publication;
    }

    public void deletePublication(String id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if(publication.isPresent()) {
            Optional<User> user = userService.get(publication.get().getOwner());
            if (user.isPresent()) {
                user.get().getPublications().remove(id);
                user.get().getFollowers().forEach(follower -> {
                    redis.opsForZSet().remove("news_feed_" + follower, id);
                });
                userService.save(user.get());
            }
        }
    }

    public void deletePublicationsFromFeed(String username, List<String> ids) {
        redis.opsForZSet().remove("news_feed_" + username, ids.stream().map(i->i).toArray(String[]::new));
    }

    public void addPublicationsOnFeed(String username, List<String> ids) {
        ids.forEach(id -> {
            redis.opsForZSet().add("news_feed_" + username, id, 1L);
        });

    }

    private String getNextPublicationId() {
        return redis.opsForValue().increment("publicationIds").toString();
    }

    private void sendToFollowers(List<String> followers, Publication publication) {
        followers.forEach(follower -> {
            redis.opsForZSet().add("news_feed_" + follower, publication.getId(), 1L);
        });
    }
}
