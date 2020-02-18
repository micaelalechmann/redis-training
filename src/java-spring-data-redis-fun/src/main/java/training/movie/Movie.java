package training.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("movies")
public class Movie{
    @Id String id;
    String name;
    Long watchCount;

    public Movie(){
        this.watchCount = 0L;
    }

    public Movie(String id,String name){
        this.id=id;
        this.name=name;
        this.watchCount = 0L;
    }

    public Movie(String name){
        this.name=name;
        this.watchCount = 0L;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " watch count: " + watchCount;
    }

    public Long getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Long watchCount) {
        this.watchCount = watchCount;
    }
}

