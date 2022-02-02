package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.dtos.DetallesParcheDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class VerDetalleParcheUseCase implements VerDetalleParche {

    private final ParcheRepository parcheRepository;
    private final InscripcionRepository inscripcionRepository;
    private final ParcheMapper parcheMapper;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final MapperUtils usuarioMapper;

    public VerDetalleParcheUseCase(ParcheRepository parcheRepository,
                                   ParcheMapper parcheMapper,
                                   InscripcionRepository inscripcionRepository,
                                   ComentarioRepository comentarioRepository,
                                   UsuarioRepository usuarioRepository,
                                   ComentarioMapper comentarioMapper,
                                   MapperUtils usuarioMapper) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioMapper = comentarioMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Mono<DetallesParcheDTO> verDetalleParche(String parcheId, String userId) {
        return parcheRepository.findById(parcheId)
                .zipWith(inscripcionRepository.findByParcheIdAndUsuarioId(parcheId, userId)
                        .switchIfEmpty(Mono.just(new Inscripcion())))
                .zipWith(inscripcionRepository.findAllByParcheId(parcheId).count()
                        .switchIfEmpty(Mono.just(0L)))
                .flatMap(datos -> {
                    var parche = datos.getT1().getT1();
                    var cantidadAsistentes = datos.getT2();
                    var inscripcion =datos.getT1().getT2();
                    var duenoDelParcheId = usuarioRepository.findByUid(parche.getDuenoDelParche());
                    var detallesParcheDTO = parcheMapper.mapToDetallesParcheDTO()
                            .apply(parche);
                    detallesParcheDTO.setCantidadAsistentes(Math.toIntExact(cantidadAsistentes));
                    detallesParcheDTO.setInscripcion(inscripcion);
                    return Mono.just(detallesParcheDTO).zipWith(duenoDelParcheId);
                })
                .flatMap(datos -> {
                    var detallesParcheDTO = datos.getT1();
                    var usuario = datos.getT2();
                    detallesParcheDTO.setDuenoDelParche(usuarioMapper.mapperEntidadUsuarioaDTO().apply(usuario));
                    return Mono.just(detallesParcheDTO);
                })
                .flatMap(getParcheDTOMonoFunction());
    }

    private Function<DetallesParcheDTO, Mono<DetallesParcheDTO>> getParcheDTOMonoFunction() {
        return detallesParcheDTO -> Mono.just(detallesParcheDTO)
                .zipWith(
                        comentarioRepository.findByParcheId(detallesParcheDTO.getId())
                                .map(comentarioMapper.comentariomapToDTO())
                                .flatMap(getUsuario())
                                .collectList(),
                        (detallesParche, comentario) -> {
                            detallesParche.setComentarioDTOS(comentario);
                            return detallesParche;
                        }
                );
    }

    private Function<ComentarioDTO, Mono<ComentarioDTO>> getUsuario() {
        return comentarioDTO -> Mono.just(comentarioDTO)
                .zipWith(
                        usuarioRepository.findByUid(comentarioDTO.getUserId())
                                .map(usuarioMapper.mapperEntidadUsuarioaDTO()),
                        (comentario, usuario) -> {
                            comentario.setUsuario(usuario);
                            return comentario;
                        }
                );
    }
}
