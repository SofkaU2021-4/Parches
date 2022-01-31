package co.com.sofka.parches.repositories;

import co.com.sofka.parches.collections.Inscripcion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InscripcionRepository extends ReactiveMongoRepository<Inscripcion, String> {
    Flux<Inscripcion> findAllByParcheId(String parcheId);
}
