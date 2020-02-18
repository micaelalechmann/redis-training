package training.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestCacheMain {
    
    public static void main(String[] args) {
        SpringApplication.run(TestCacheMain.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunnerMapper(ApplicationContext ctx) {
        return args -> {

            System.out.println("Running Spring Boot application in Console... ");

            CacheService service = ctx.getBean(CacheService.class);
            service.set(new CacheObject("1", "test"));
            System.out.println(service.get("1").get());
        };
    }

}

