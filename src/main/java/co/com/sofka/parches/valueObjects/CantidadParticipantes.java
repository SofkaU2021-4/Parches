package co.com.sofka.parches.valueObjects;

import java.util.Objects;

public class CantidadParticipantes {
    private Long valorCantidadParticipantes;

    public CantidadParticipantes() {
    }
  
    public CantidadParticipantes(Long valorCantidadParticipantes){
        this.valorCantidadParticipantes = valorCantidadParticipantes;
    }

    public Long getValorCantidadParticipantes(){
        return valorCantidadParticipantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CantidadParticipantes that = (CantidadParticipantes) o;
        return Objects.equals(valorCantidadParticipantes, that.valorCantidadParticipantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorCantidadParticipantes);
    }

    @Override
    public String toString() {
        return "CantidadParticipantes{" +
                "valor=" + valorCantidadParticipantes +
                '}';
    }
}
