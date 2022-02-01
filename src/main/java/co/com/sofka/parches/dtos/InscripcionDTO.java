package co.com.sofka.parches.dtos;

import co.com.sofka.parches.valueObjects.FechaParche;
import org.springframework.data.annotation.Id;

public class InscripcionDTO {

    private String id;
    private String parcheId;
    private String usuarioId;
    private FechaParche fechaDeInscripcion;

    public InscripcionDTO() {
    }

    public InscripcionDTO(String id,
                          String parcheId,
                          String usuarioId,
                          FechaParche fechaDeInscripcion) {
        this.id = id;
        this.parcheId = parcheId;
        this.usuarioId = usuarioId;
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParcheId() {
        return parcheId;
    }

    public void setParcheId(String parcheId) {
        this.parcheId = parcheId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public FechaParche getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(FechaParche fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }
}
