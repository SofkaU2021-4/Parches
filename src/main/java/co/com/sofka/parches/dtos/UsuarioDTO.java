package co.com.sofka.parches.dtos;

import javax.validation.constraints.NotBlank;

public class UsuarioDTO {
    private String id;
    @NotBlank
    private String uid;
    private String nombres;
    private String email;
    private String imagenUrl;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String uid, String nombres, String email, String imagenUrl) {
        this.id = id;
        this.uid = uid;
        this.nombres = nombres;
        this.email = email;
        this.imagenUrl=imagenUrl;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
