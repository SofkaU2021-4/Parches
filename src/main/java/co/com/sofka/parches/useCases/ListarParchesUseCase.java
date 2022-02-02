package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.CantidadParticipantes;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ListarParchesUseCase {
    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;
    private final InscripcionRepository inscripcionRepository;

    public ListarParchesUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper, InscripcionRepository inscripcionRepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
    }

    public Flux<ParcheDTO> apply(){
            return parcheRepository.findAll()
                    .flatMap(parche ->
                            inscripcionRepository.findAllByParcheId(parche.getId()).count().flatMap(valor -> {
                                ParcheDTO parcheDTO = parcheMapper.mapToDTO().apply(parche);
                                parcheDTO.setCantidadParticipantes(new CantidadParticipantes(valor.longValue()));
                                return Mono.just(parcheDTO);
                            })
                    );
    }
}
