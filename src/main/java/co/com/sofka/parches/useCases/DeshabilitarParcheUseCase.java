package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.utils.ValidadorParche;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeshabilitarParcheUseCase implements DeshabilitarParche{

    private final ParcheRepository parcheRepository;
    private final InscripcionRepository inscripcionRepository;
    private final ParcheMapper parcheMapper;

    public DeshabilitarParcheUseCase(ParcheRepository parcheRepository,
                                     ParcheMapper parcheMapper,
                                     InscripcionRepository inscripcionRepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Mono<ParcheDTO> deshabilitarParche(ParcheDTO parcheDTO) {
         if(Boolean.TRUE.equals(ValidadorParche.validarParcheHabilitado(parcheDTO.getEstado()))){
             parcheDTO.setEstado(Estado.DESHABILITADO);
             return inscripcionRepository.deleteAllByParcheId(parcheDTO.getId())
                     .then(parcheRepository.save(parcheMapper.mapToCollection()
                                     .apply(ValidadorParche.validarParche(parcheDTO)))
                             .map(parcheMapper.mapToDTO()));
         }
         return Mono.just(parcheDTO);
    }

}
