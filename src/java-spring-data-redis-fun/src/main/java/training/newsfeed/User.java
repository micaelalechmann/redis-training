package training.newsfeed;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash("user")
public class User {

    @Id
    private String username;
    private List<String> followers;
    private List<String> following;
    private List<String> publications;

    public User() {
        this.followers = new ArrayList<String>();
        this.following = new ArrayList<String>();
        this.publications = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", publications=" + publications +
                '}';
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}
