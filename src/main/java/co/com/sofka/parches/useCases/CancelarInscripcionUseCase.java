package co.com.sofka.parches.useCases;


import co.com.sofka.parches.repositories.InscripcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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
        return inscripcionRepository.deleteById(inscripcionId);
    }


}
