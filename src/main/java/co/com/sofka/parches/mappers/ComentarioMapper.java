package co.com.sofka.parches.mappers;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.dtos.ComentarioDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ComentarioMapper {

    public Function<Comentario, ComentarioDTO> comentariomapToDTO(){
        return comentario ->
        {
            var comentariodto=new ComentarioDTO(
                    comentario.getId(),
                    comentario.getUserId(),
                    comentario.getParcheId(),
                    comentario.getMensaje());
            comentariodto.setFechaCreacion(comentario.getFechaCreacion());
        return comentariodto;
        };
    }

    public Function<ComentarioDTO, Comentario> comentariomapToCollection(){
        return comentariodto -> {
            Comentario comentario = new Comentario();
            comentario.setId(comentariodto.getId());
            comentario.setUserId(comentariodto.getUserId());
            comentario.setParcheId(comentariodto.getParcheId());
            comentario.setMensaje(comentariodto.getMensaje());
            comentario.setFechaCreacion(comentariodto.getFechaCreacion());
            return comentario;
        };
    }
}
