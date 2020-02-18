package training.userprofile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestUserProfileMain {
    
    public static void main(String[] args) {
        SpringApplication.run(TestUserProfileMain.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunnerMapper(ApplicationContext ctx) {
        return args -> {

            System.out.println("Running Spring Boot application in Console... ");

            UserProfileService service = ctx.getBean(UserProfileService.class);
            UserProfile userProfile = new UserProfile();
            userProfile.setAddress("washington luiz");
            userProfile.setDateOfBirth("30/11/2000");
            userProfile.setEmail("lala@lala.com");
            userProfile.setName("lala");
            userProfile.setTwitterHandler("n sei oq eh");

            service.save(userProfile);
            System.out.println(service.get("lala@lala.com"));

            userProfile.setTwitterHandler("agr sei oq eh");
            userProfile.setAddress("independencia");
            service.save(userProfile);
            System.out.println(service.get("lala@lala.com"));
        };
    }

}

