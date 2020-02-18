package movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestMovieMain {
    
    public static void main(String[] args) {
        SpringApplication.run(TestMovieMain.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunnerMapper(ApplicationContext ctx) {
        return args -> {

            System.out.println("Running Spring Boot application in Console... ");

            MovieService service = ctx.getBean(MovieService.class);
            MovieRepository repo = ctx.getBean(MovieRepository.class);
            Movie movie = new Movie("movie lala");
            service.createMovie(movie);
            service.watch("1");
            System.out.println("Movie: " + service.getMovie("123"));
            System.out.println("All movies" + service.getAll());
        };
    }

}

