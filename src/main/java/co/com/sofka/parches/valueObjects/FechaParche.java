package co.com.sofka.parches.valueObjects;

import java.time.LocalDateTime;

public class FechaParche {

    private LocalDateTime valorFecha;

    public FechaParche(){}

    public FechaParche(LocalDateTime fecha){
        this.valorFecha = fecha;
    }

    public Boolean esFechaAnterior(FechaParche fecha){
        return this.valorFecha.isBefore(fecha.getValorFecha());
    }

    public LocalDateTime getValorFecha() {
        return valorFecha;
    }
}
