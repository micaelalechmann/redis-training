package movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    RedisTemplate<String,String> redis;
    @Autowired
    MovieRepository movieRepo;

    public Set<String> getAllMovies(String key) {
        return redis.opsForSet().members(key);
    }

    public Movie createMovie(Movie movie) {
        movie.setId(getNextId());
        return movieRepo.save(movie);
    }

    public Optional<Movie> getMovie(String id) {
        return movieRepo.findById(id);
    }

    public Iterable<Movie> getAll() {
        return movieRepo.findAll();
    }

    public void watch(String id) {
        redis.opsForHash().increment("movies:" + id, "watchCount", 1L);
    }

    private String getNextId() {
        return redis.opsForValue().increment("movieId").toString();
    }
}
