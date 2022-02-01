package co.com.sofka.parches.mappers;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.dtos.ComentarioDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ComentarioMapper {

    public Function<Comentario, ComentarioDTO> comentariomapToDTO(){
        return comentario -> new ComentarioDTO(
                comentario.getId(),
                comentario.getUserId(),
                comentario.getParcheId(),
                comentario.getComentario(),
                comentario.getFechaCreacion());
    }

    public Function<ComentarioDTO, Comentario> comentariomapToCollection(){
        return ComentarioDTO -> {
            Comentario comentario = new Comentario();
            comentario.setId(ComentarioDTO.getId());
            comentario.setUserId(ComentarioDTO.getUserId());
            comentario.setParcheId(ComentarioDTO.getParcheId());
            comentario.setComentario(ComentarioDTO.getComentario());
            comentario.setFechaCreacion(ComentarioDTO.getFechaCreacion());
            return comentario;
        };
    }
}
