package co.com.sofka.parches.useCases;


import co.com.sofka.parches.repositories.InscripcionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class CancelarInscripcionUseCase {

    private final InscripcionRepository inscripcionRepository;


    public CancelarInscripcionUseCase(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public Mono<Void> cancelarInscripcion(String inscripcionId){
        Objects.requireNonNull(inscripcionId,"para cancelar inscripcion se necesita el id");
        return  inscripcionRepository.findById(inscripcionId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se encontro el parche")))
                .and(inscripcionRepository.deleteById(inscripcionId));
    }


}
