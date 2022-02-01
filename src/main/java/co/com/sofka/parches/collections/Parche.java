package co.com.sofka.parches.collections;

import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.valueObjects.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value="parches")

public class Parche {

    @Id
    private String id;
    private String duenoDelParche;
    private NombreParche nombreParche;
    private DescripcionParche descripcion;
    private FechaParche fechaCreacion;
    private FechaParche fechaInicio;
    private FechaParche fechaFin;
    private Estado estado;
    private Categoria categoria;
    private CapacidadParche capacidadMaxima;
    private UbicacionParche ubicacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuenoDelParche() {
        return duenoDelParche;
    }

    public void setDuenoDelParche(String duenoDelParche) {
        this.duenoDelParche = duenoDelParche;
    }

    public NombreParche getNombreParche() {
        return nombreParche;
    }

    public void setNombreParche(NombreParche nombreParche) {
        this.nombreParche = nombreParche;
    }

    public DescripcionParche getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(DescripcionParche descripcion) {
        this.descripcion = descripcion;
    }

    public FechaParche getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(FechaParche fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public FechaParche getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(FechaParche fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public FechaParche getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(FechaParche fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CapacidadParche getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(CapacidadParche capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public UbicacionParche getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionParche ubicacion) {
        this.ubicacion = ubicacion;
    }
}
