package co.com.sofka.parches.useCases;

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
                }
                ).flatMap(getParcheDTOMonoFunction());
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
                        usuarioRepository.findById(comentarioDTO.getUserId())
                                .map(usuarioMapper.mapperEntidadUsuarioaDTO()),
                        (comentario, usuario) -> {
                            comentario.setUsuario(usuario);
                            return comentario;
                        }
                );
    }
}
