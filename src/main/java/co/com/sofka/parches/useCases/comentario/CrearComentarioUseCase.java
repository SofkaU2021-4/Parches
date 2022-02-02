package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

@Service
@Validated
public class CrearComentarioUseCase implements Function<ComentarioDTO, Mono<ComentarioDTO>> {

    private final ComentarioMapper comentarioMapper;
    private final ParcheRepository parcheRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final MapperUtils mapperUtils;


    public CrearComentarioUseCase(ComentarioMapper comentarioMapper, ParcheRepository parcheRepository, ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, MapperUtils mapperUtils) {
        this.comentarioMapper = comentarioMapper;
        this.parcheRepository = parcheRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<ComentarioDTO> apply(ComentarioDTO comentarioDTO) {
        comentarioDTO.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Bogota")));
        return parcheRepository.findById(comentarioDTO.getParcheId())
                .flatMap(parche -> {
                    return usuarioRepository.findByUid(comentarioDTO.getUserId())
                            .flatMap(usuario -> {
                                return comentarioRepository.save(comentarioMapper.comentariomapToCollection()
                                                .apply(comentarioDTO))
                                        .map(comentario->{
                                            var comentarioDto= comentarioMapper.comentariomapToDTO()
                                                    .apply(comentario);
                                            var usuarioDto=mapperUtils.mapperEntidadUsuarioaDTO()
                                                    .apply(usuario);
                                            comentarioDto.setUsuario(usuarioDto);
                                            return comentarioDto;
                                                });
                            })
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no Existe")));
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST , "Parche no Existe")));
    }
}
