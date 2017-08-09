package pl.sscode.onepass.repository.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sscode on 2017-06-15.
 */
@SpringBootApplication
@ComponentScan("pl.sscode")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
