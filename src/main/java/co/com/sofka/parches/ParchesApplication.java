package co.com.sofka.parches;

import co.com.sofka.parches.utils.Firebase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
public class ParchesApplication {

    static final Logger log = Logger.getLogger("principal");

    public static void main(String[] args) {
        SpringApplication.run(ParchesApplication.class, args);
        try {
            Firebase.inicializarFirebase();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

}
