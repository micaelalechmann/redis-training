package training.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheService {

    @Autowired
    RedisOperations<String,Object> redis;

    public Optional<CacheObject> get(String id) {
        return Optional.of((CacheObject) redis.opsForValue().get("cache_" + id));
    }

    public void set(CacheObject cacheObject) {
        redis.opsForValue().set("cache_" + cacheObject.getKey(), cacheObject);
    }
}
