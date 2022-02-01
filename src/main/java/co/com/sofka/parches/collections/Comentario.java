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
    private String comentario;
    private LocalDateTime fechaCreacion;

    public Comentario(String id, String userId, String parcheId, String comentario) {
        this.id = id;
        this.userId = userId;
        this.parcheId = parcheId;
        this.comentario = comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
