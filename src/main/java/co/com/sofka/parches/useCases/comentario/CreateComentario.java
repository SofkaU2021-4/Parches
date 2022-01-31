package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.dtos.ComentarioDTO;
import reactor.core.publisher.Mono;

public interface CreateComentario {
    Mono<Comentario> apply(ComentarioDTO comentarioDTO);
}
