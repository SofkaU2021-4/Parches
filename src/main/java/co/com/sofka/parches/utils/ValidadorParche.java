package co.com.sofka.parches.utils;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.valueObjects.*;

public class ValidadorParche {

    public static Boolean validarCapacidad(CapacidadParche capacidad){
        return capacidad.getValorCapacidad() > 0;
    }

    public static Boolean validarNombreParche(NombreParche nombre){
        return nombre.getValorNombre().length() <= 50 && nombre.getValorNombre().matches("[a-zA-z\\s]*");
    }

    public static Boolean validarFechaInicioParche(FechaParche fechaCreacion, FechaParche fechaInicio){
        return fechaCreacion.esFechaAnterior(fechaInicio);
    }

    public static Boolean validarFechaFinParche(FechaParche fechaInicio, FechaParche fechaFin){
        return fechaInicio.esFechaAnterior(fechaFin);
    }

    public static Boolean validarDescripcionParche(DescripcionParche descripcionParche){
        return descripcionParche!=null;
    }

    public static Boolean validarUbicacionParche(UbicacionParche ubicacionParche){
        return ubicacionParche.getX() != null && ubicacionParche.getY() != null;
    }

    public static ParcheDTO validarCrearParche(ParcheDTO parcheDTO){
        if(Boolean.FALSE.equals(validarNombreParche(parcheDTO.getNombreParche()))){
            throw new IllegalArgumentException("Error: Nombre invalido");
        }
        if(Boolean.FALSE.equals(validarCapacidad(parcheDTO.getCapacidadMaxima()))){
            throw new IllegalArgumentException("Error: Capacidad invalida");
        }
        if(Boolean.FALSE.equals(validarFechaInicioParche(parcheDTO.getFechaCreacion(), parcheDTO.getFechaInicio()))){
            throw new IllegalArgumentException("Error: la fecha de inicio no puede ser anterior a la fecha actual");
        }
        if(Boolean.FALSE.equals(validarFechaFinParche(parcheDTO.getFechaInicio(), parcheDTO.getFechaFin()))){
            throw new IllegalArgumentException("Error: la fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if(Boolean.FALSE.equals(validarDescripcionParche(parcheDTO.getDescripcion()))){
            parcheDTO.setDescripcion(new DescripcionParche(""));
        }
        if(Boolean.FALSE.equals(validarUbicacionParche(parcheDTO.getUbicacionParche()))){
            throw new IllegalArgumentException("Error: tanto la latitud como la longitud deben ser no nulas");
        }

        return parcheDTO;
    }

}
