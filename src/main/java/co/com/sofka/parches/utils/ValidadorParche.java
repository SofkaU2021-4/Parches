package co.com.sofka.parches.utils;

import co.com.sofka.parches.valueObjects.CapacidadParche;
import co.com.sofka.parches.valueObjects.FechaParche;
import co.com.sofka.parches.valueObjects.NombreParche;

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

}
