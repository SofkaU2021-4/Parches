package co.com.sofka.parches.valueObjects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FechaParche implements Serializable {

    private LocalDateTime valorFecha;

    public FechaParche(){
    }

    public FechaParche(String fecha){
        this.valorFecha = LocalDateTime.parse(fecha);
    }

    public Boolean esFechaAnterior(FechaParche fecha){
        return this.valorFecha.isBefore(fecha.getValorFecha());
    }

    public LocalDateTime getValorFecha() {
        return valorFecha;
    }
}
