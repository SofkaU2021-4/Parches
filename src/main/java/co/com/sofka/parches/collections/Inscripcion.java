package co.com.sofka.parches.collections;

import co.com.sofka.parches.valueObjects.FechaParche;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Inscripciones")
public class Inscripcion {

    @Id
    private String id;
    private String parcheId;
    private String usuarioId;
    private FechaParche fechaDeinscripcion;

    public String getId() {
        return id;
    }

    public String getParcheId() {
        return parcheId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public FechaParche getFechaDeinscripcion() {
        return fechaDeinscripcion;
    }
}
