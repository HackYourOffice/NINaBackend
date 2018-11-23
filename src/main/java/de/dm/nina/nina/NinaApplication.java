package de.dm.nina.nina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@SpringBootApplication
@EnableJpaRepositories
public class NinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinaApplication.class, args);
    }
}
