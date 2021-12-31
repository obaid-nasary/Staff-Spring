package com.example.demo.staff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StaffConfig {

    @Bean
    CommandLineRunner commandLineRunner(StaffRepository repository){
        return args -> {
            Staff khalid = new Staff(
                    1L,
                    "Khalid",
                    "khalid@gmail.com",
                    LocalDate.of(2009, Month.FEBRUARY, 9)
            );
            Staff walid = new Staff(
                    "Walid",
                    "walid@gmail.com",
                    LocalDate.of(2000, Month.DECEMBER, 27)
            );
            Staff nejrab = new Staff(
                    "Nejrab",
                    "nejrab@gmail.com",
                    LocalDate.of(2007, Month.SEPTEMBER, 13)
            );
            Staff mureed = new Staff(
                    "Mureed",
                    "mureed@gmail.com",
                    LocalDate.of(1998, Month.OCTOBER, 7)
            );
            repository.saveAll(
                    List.of(khalid, walid, nejrab, mureed)
            );
        };
    }
}
