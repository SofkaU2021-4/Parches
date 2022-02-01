package co.com.sofka.parches.repositories;

import co.com.sofka.parches.collections.Parche;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ParcheRepository extends ReactiveMongoRepository<Parche, String> {
    Flux<Parche> findAllByDuenoDelParche(String duenoDelParche);
}
