package co.com.sofka.parches.mappers;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.dtos.InscripcionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InscripcionMapper {

    public Function<Inscripcion, InscripcionDTO> mapEntityToInscripcionDTO(){
        return inscripcion -> new InscripcionDTO(
                inscripcion.getId(),
                inscripcion.getParcheId(),
                inscripcion.getUsuarioId(),
                inscripcion.getFechaDeInscripcion());
    }



}
