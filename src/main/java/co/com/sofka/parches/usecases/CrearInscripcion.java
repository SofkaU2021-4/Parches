package co.com.sofka.parches.usecases;

import co.com.sofka.parches.dtos.InscripcionDTO;
import reactor.core.publisher.Mono;

public interface CrearInscripcion {

    Mono<InscripcionDTO> crearInscripcion(String parcheId, String usuarioId);
}
