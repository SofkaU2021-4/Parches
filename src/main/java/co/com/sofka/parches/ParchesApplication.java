package co.com.sofka.parches;

import co.com.sofka.parches.utils.Firebase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ParchesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParchesApplication.class, args);
        try {
            Firebase.conectar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
