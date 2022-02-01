package co.com.sofka.parches.repositories;

import co.com.sofka.parches.collections.Inscripcion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InscripcionRepository extends ReactiveMongoRepository<Inscripcion, String> {

//    Mono<Inscripcion> existsByIdAndUsuarioId(String parcheId, String userId);

    Mono<Inscripcion> findByParcheIdAndUsuarioId(String parcheId, String userId);

}
