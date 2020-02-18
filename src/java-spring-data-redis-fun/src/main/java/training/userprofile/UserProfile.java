package training.userprofile;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user_profile")
public class UserProfile {

    @Id
    private String email;
    private String name;
    private String address;
    private String dateOfBirth;
    private String twitterHandler;

    public UserProfile() {}

    @Override
    public String toString() {
        return "UserProfile{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", twitterHandler='" + twitterHandler + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTwitterHandler() {
        return twitterHandler;
    }

    public void setTwitterHandler(String twitterHandler) {
        this.twitterHandler = twitterHandler;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
