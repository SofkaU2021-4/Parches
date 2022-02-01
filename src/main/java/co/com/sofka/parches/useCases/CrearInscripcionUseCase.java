package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.dtos.InscripcionDTO;
import co.com.sofka.parches.mappers.InscripcionMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.valueObjects.FechaParche;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Validated
public class CrearInscripcionUseCase implements CrearInscripcion {

    private final InscripcionRepository inscripcionRepository;
    private final InscripcionMapper inscripcionMapper;

    public CrearInscripcionUseCase(InscripcionRepository inscripcionRepository,
                                   InscripcionMapper inscripcionMapper) {
        this.inscripcionRepository = inscripcionRepository;
        this.inscripcionMapper = inscripcionMapper;
    }

    @Override
    public Mono<InscripcionDTO> crearInscripcion(String parcheId, String usuarioId) {
        var inscripcion = new Inscripcion(parcheId, usuarioId, new FechaParche(LocalDateTime.now().toString()));
        return inscripcionRepository.save(inscripcion)
                .map(inscripcionMapper.mapEntityToInscripcionDTO());
    }
}
