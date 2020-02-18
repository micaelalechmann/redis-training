package training.newsfeed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import training.userprofile.UserProfile;
import training.userprofile.UserProfileService;

@SpringBootApplication
public class TestNewsFeedMain {
    
    public static void main(String[] args) {
        SpringApplication.run(TestNewsFeedMain.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerMapper(ApplicationContext ctx) {
        return args -> {

            System.out.println("Running Spring Boot application in Console... ");

            UserService userService = ctx.getBean(UserService.class);
            PublicationService publicationService = ctx.getBean(PublicationService.class);

            User userA = new User();
            userA.setUsername("userA");
            User userB = new User();
            userB.setUsername("userB");
            User userC = new User();
            userC.setUsername("userC");
            User userD = new User();
            userD.setUsername("userD");

            Publication publicationA1 = new Publication();
            publicationA1.setTitle("title A1 - first publication");
            publicationA1.setContent("blablabla");

            Publication publicationA2 = new Publication();
            publicationA2.setTitle("title A2 - second publication");
            publicationA2.setContent("blablabla");

            Publication publicationA3 = new Publication();
            publicationA3.setTitle("title A3");
            publicationA3.setContent("blablabla");

            userService.save(userA);
            userService.save(userB);
            userService.save(userC);
            userService.save(userD);

            userService.follow(userB.getUsername(), userA.getUsername());
            userService.follow(userC.getUsername(), userA.getUsername());

            publicationA1 = publicationService.publish(userA.getUsername(), publicationA1);
            publicationA2 = publicationService.publish(userA.getUsername(), publicationA2);
            publicationA3 = publicationService.publish(userA.getUsername(), publicationA3);

            System.out.println(publicationService.getNewsFeed(userB.getUsername()));

            publicationService.get(publicationA2.getId());
            publicationService.get(publicationA2.getId());
            publicationService.get(publicationA2.getId());
            publicationService.get(publicationA3.getId());

            System.out.println(publicationService.getNewsFeed(userB.getUsername()));

            publicationService.deletePublication(publicationA1.getId());

            System.out.println(publicationService.getNewsFeed(userB.getUsername()));

            userService.unfollow(userB.getUsername(), userA.getUsername());

            System.out.println(publicationService.getNewsFeed(userB.getUsername()));
        };
    }

}

