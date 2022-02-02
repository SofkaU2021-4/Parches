package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.CantidadParticipantes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class ListarMisParchesCreadosUseCase implements ListarMisParchesCreados {

    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;
    private final InscripcionRepository inscripcionRepository;

    public ListarMisParchesCreadosUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper, InscripcionRepository inscripcionRepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Flux<ParcheDTO> listarMisParchesCreados(String duenoDelParche) {
        Objects.requireNonNull(duenoDelParche, "Id del dueño del parche es requerido");
        return parcheRepository.findAllByDuenoDelParche(duenoDelParche).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no Existe")))
                .filter(parcheDTO -> parcheDTO.getEstado().equals(Estado.HABILITADO))
                .map(parcheMapper.mapToDTO())
                .flatMap(contarParticipantesParche());

    }

    private Function<ParcheDTO, Flux<ParcheDTO>> contarParticipantesParche() {
        return parcheDto ->
                Flux.just(parcheDto).zipWith(
                        inscripcionRepository.findAllByParcheId(parcheDto.getId())
                                .collectList(),
                        (parche, inscripciones) -> {
                            parche.setCantidadParticipantes(new CantidadParticipantes((long) inscripciones.size()));
                            return parche;
                        });
    }
}
