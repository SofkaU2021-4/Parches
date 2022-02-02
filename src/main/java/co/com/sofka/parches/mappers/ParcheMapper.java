package co.com.sofka.parches.mappers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.dtos.DetallesParcheDTO;
import co.com.sofka.parches.dtos.ParcheDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ParcheMapper {

    public Function<Parche, ParcheDTO> mapToDTO(){
        return parche -> new ParcheDTO(parche.getId(),
                parche.getDuenoDelParche(),
                parche.getNombreParche().getValorNombre(),
                parche.getDescripcion().getValorDescripcion(),
                parche.getFechaCreacion().getValorFecha().toString(),
                parche.getFechaInicio().getValorFecha().toString(),
                parche.getFechaFin().getValorFecha().toString(),
                parche.getEstado(),
                parche.getCategoria(),
                parche.getCapacidadMaxima().getValorCapacidad(),
                parche.getUbicacion());
    }

    public Function<ParcheDTO, Parche> mapToCollection(){
        return parcheDTO -> {
            Parche parche = new Parche();
            parche.setId(parcheDTO.getId());
            parche.setDuenoDelParche(parcheDTO.getDuenoDelParche());
            parche.setNombreParche(parcheDTO.getNombreParche());
            parche.setDescripcion(parcheDTO.getDescripcion());
            parche.setFechaCreacion(parcheDTO.getFechaCreacion());
            parche.setFechaInicio(parcheDTO.getFechaInicio());
            parche.setFechaFin(parcheDTO.getFechaFin());
            parche.setEstado(parcheDTO.getEstado());
            parche.setCategoria(parcheDTO.getCategoria());
            parche.setCapacidadMaxima(parcheDTO.getCapacidadMaxima());
            parche.setUbicacion(parcheDTO.getUbicacionParche());
            return parche;
        };
    }

    public Function<Parche, DetallesParcheDTO> mapToDetallesParcheDTO(){
        return parche -> new DetallesParcheDTO(
                parche.getId(),
                parche.getNombreParche(),
                parche.getDescripcion(),
                parche.getFechaCreacion(),
                parche.getFechaInicio(),
                parche.getFechaFin(),
                parche.getCategoria(),
                parche.getCapacidadMaxima(),
                parche.getUbicacion()
        );
    }
}

