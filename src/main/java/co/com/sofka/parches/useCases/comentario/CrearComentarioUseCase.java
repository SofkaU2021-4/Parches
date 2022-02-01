package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
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


    public CrearComentarioUseCase(ComentarioMapper comentarioMapper, ParcheRepository parcheRepository, ComentarioRepository comentarioRepository) {
        this.comentarioMapper = comentarioMapper;
        this.parcheRepository = parcheRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Mono<ComentarioDTO> apply(ComentarioDTO comentarioDTO) {
        comentarioDTO.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Bogota")));
        return parcheRepository.findById(comentarioDTO.getParcheId())
                .flatMap(parche -> {
                    return comentarioRepository.save(comentarioMapper.comentariomapToCollection().apply(comentarioDTO))
                            .map(comentarioMapper.comentariomapToDTO());
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST , "Parche no Existe")));
    }
}
