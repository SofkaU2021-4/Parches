package co.com.sofka.parches.repositories;

import co.com.sofka.parches.collections.Parche;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcheRepository extends ReactiveMongoRepository<Parche, String> {
}
