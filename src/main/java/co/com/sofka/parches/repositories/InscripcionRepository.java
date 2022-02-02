package co.com.sofka.parches.repositories;

import reactor.core.publisher.Mono;
import co.com.sofka.parches.collections.Inscripcion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InscripcionRepository extends ReactiveMongoRepository<Inscripcion, String> {
    Flux<Inscripcion> findAllByParcheId(String parcheId);
    Mono<Long> countAllByParcheId(String parcheId);
    Mono<Inscripcion> existsByIdAndUsuarioId(String parcheId, String userId);
    Mono<Inscripcion> findByParcheIdAndUsuarioId(String parcheId, String userId);
    Mono<Void> deleteAllByParcheId(String parcheId);
}
