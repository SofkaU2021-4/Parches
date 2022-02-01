package co.com.sofka.parches.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class ComentarioDTO {
    private String id;
    private String userId;
    private String parcheId;
    private String comentario;
    private UsuarioDTO usuario;
    private LocalDateTime fechaCreacion;


    public ComentarioDTO(String id, String userId, String parcheId, String comentario) {
        if(!(comentario.length() > 0 && comentario.length() <= 256)){
            throw new IllegalArgumentException("El comentario no debe contener mas de 256 caracteres");
        }
        this.id = id;
        this.userId = userId;
        this.parcheId = parcheId;
        this.comentario = comentario;
    }



    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioDTO that = (ComentarioDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(parcheId, that.parcheId) && Objects.equals(comentario, that.comentario) && Objects.equals(fechaCreacion, that.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, parcheId, comentario, fechaCreacion);
    }
}
