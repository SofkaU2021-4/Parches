package co.com.sofka.parches.repositories;

import co.com.sofka.parches.collections.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {
    Mono<Usuario> findByUid(String uid);
}
