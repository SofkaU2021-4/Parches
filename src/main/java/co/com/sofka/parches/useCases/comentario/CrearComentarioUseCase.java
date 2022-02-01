package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

@Service
@Validated
public class CrearComentarioUseCase implements Function<ComentarioDTO, Mono<ComentarioDTO>> {

    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepository comentarioRepository;

    public CrearComentarioUseCase(ComentarioMapper comentarioMapper, ComentarioRepository comentarioRepository) {
        this.comentarioMapper = comentarioMapper;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Mono<ComentarioDTO> apply(ComentarioDTO comentarioDTO) {
        comentarioDTO.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Bogota")));
        return comentarioRepository.save(comentarioMapper.comentariomapToCollection().apply(comentarioDTO))
                .map(comentarioMapper.comentariomapToDTO());
    }
}
