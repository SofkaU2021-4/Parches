package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.DetallesParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@Validated
public class VerDetalleParcheUseCase implements VerDetalleParche {

    private final ParcheRepository parcheRepository;
    private final InscripcionRepository inscripcionRepository;
    private final ParcheMapper parcheMapper;

    public VerDetalleParcheUseCase(ParcheRepository parcheRepository,
                                   ParcheMapper parcheMapper,
                                   InscripcionRepository inscripcionRepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Mono<DetallesParcheDTO> verDetalleParche(String parcheId, String userId) {
        return parcheRepository.findById(parcheId)
                .zipWith(inscripcionRepository.findByParcheIdAndUsuarioId(parcheId, userId))
                .zipWith(inscripcionRepository.findAll().count()).flatMap(datos -> {
                    var extraccionDatos = datos.getT1();
                    var extraccionDatos2 = datos.getT2();
                    var detallesParcheDTO = parcheMapper.mapToDetallesParcheDTO()
                            .apply(extraccionDatos.getT1());
                    var inscripcion = extraccionDatos.getT2();
                    detallesParcheDTO.setCantidadAsistentes(Math.toIntExact(extraccionDatos2));
                    detallesParcheDTO.setInscripcion(inscripcion);
                    return Mono.just(detallesParcheDTO);
                });
    }
}
