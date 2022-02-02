package co.com.sofka.parches.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Document
public class Comentario {
    @Id
    private String id;
    private String userId;
    private String parcheId;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    public Comentario(String id, String userId, String parcheId, String mensaje) {
        this.id = id;
        this.userId = userId;
        this.parcheId = parcheId;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now(ZoneId.of("America/Bogota"));
    }

    public Comentario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParcheId() {
        return parcheId;
    }

    public void setParcheId(String parcheId) {
        this.parcheId = parcheId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
