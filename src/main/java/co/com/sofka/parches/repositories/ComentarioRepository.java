package co.com.sofka.parches.repositories;


import co.com.sofka.parches.collections.Comentario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComentarioRepository extends ReactiveMongoRepository<Comentario, String> {
    Mono<Void> deleteByParcheId (String parcheId);
    Flux<Comentario> findByParcheId (String parcheId);
    Mono<Void> deleteAllByParcheId(String parcheId);
}
