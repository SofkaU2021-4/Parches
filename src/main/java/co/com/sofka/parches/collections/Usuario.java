package co.com.sofka.parches.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document
public class Usuario {
    @Id
    private String id;
    private String uid;
    @Size(max = 50, message = "Maximo 50 caracteres")
    private String nombres;
    @Indexed(unique = true)
    private String email;
    private String imagenUrl;

    public Usuario() {
    }

    public Usuario(String id, String uid, String nombres, String email, String imagenUrl) {
        this.id = id;
        this.uid = uid;
        this.nombres = nombres;
        this.email = email;
        this.imagenUrl = imagenUrl;
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
