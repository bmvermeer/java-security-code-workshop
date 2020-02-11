package io.snyk.demo;


import io.snyk.demo.domain.User;
import io.snyk.demo.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(UserRepo userRepo) {
        return (args) -> {
            // save a few of items to the grocery list


            userRepo.save(new User("Bruce", "Wayne", "Batman", "ToTheBadmobil", "lives in a cave in gotham"));
            userRepo.save(new User("Peter", "Parker", "Spiderman", "FriendlyNeighborhood", "bitten by a spider, lives in NYC"));
            userRepo.save(new User("Tony", "Stark", "Ironman", "HeyJarvis", "Owner of Stark Industries"));
            userRepo.save(new User("Clark", "Kent", "Superman", "Kryptonite", "From the planet Krypton with the name Kal-El"));
            userRepo.save(new User("Steve", "Rogers", "CaptainAmerica", "SirYesSir", "A patriotic supersoldier who fought in World War II"));
            userRepo.save(new User("Barry", "Allen", "Flash", "SpeedOfLight", "Speedster metahuman that lives in Central City"));


            // fetch all items on the grocery list
            System.out.println("Messages found with");
            System.out.println("-------------------------------");
            userRepo.findAll().forEach(System.out::println);

        };
    }


}

