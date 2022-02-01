package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.repositories.ComentarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarComentarioUsecase implements Function<String, Mono<String>> {
    private final ComentarioRepository comentarioRepository;

    public EliminarComentarioUsecase(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        return comentarioRepository.findById(id)
                .flatMap(comentario -> {
                    return comentarioRepository.deleteById(comentario.getId()).thenReturn("Eliminado");
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST , "id Incorrecto")));
    }

}
