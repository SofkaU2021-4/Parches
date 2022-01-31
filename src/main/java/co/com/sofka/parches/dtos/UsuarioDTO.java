package co.com.sofka.parches.dtos;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UsuarioDTO {
    private String id;
    @NotBlank
    private String uid;
    private String nombres;
    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String uid, String nombres, String email) {
        this.id = id;
        this.uid = uid;
        this.nombres = nombres;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioDTO)) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(uid, that.uid) && Objects.equals(nombres, that.nombres) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, nombres, email);
    }

}
