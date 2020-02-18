package training.movie;

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
            Movie movie1 = new Movie("Parasite");
            Movie movie2 = new Movie("Jojo Rabbit");
            Movie movie3 = new Movie("1917");
            Movie movie4 = new Movie("Ford vs Ferrari");
            service.createMovie(movie1);
            service.createMovie(movie2);
            service.createMovie(movie3);
            service.createMovie(movie4);
            service.watch("1");
            service.watch("2");
            service.watch("2");
            service.watch("3");
            System.out.println("All movies" + service.getAll());
        };
    }

}

