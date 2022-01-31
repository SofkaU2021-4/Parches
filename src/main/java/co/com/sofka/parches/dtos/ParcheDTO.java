package co.com.sofka.parches.dtos;

import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.valueObjects.*;
import com.mongodb.lang.NonNull;

public class ParcheDTO {

    private String id;
    @NonNull
    private String duenoDelParche;
    @NonNull
    private NombreParche nombreParche;

    private DescripcionParche descripcion;
    private FechaParche fechaCreacion;
    @NonNull
    private FechaParche fechaInicio;
    @NonNull
    private FechaParche fechaFin;

    private Estado estado;
    @NonNull
    private Categoria categoria;
    @NonNull
    private CapacidadParche capacidadMaxima;
    @NonNull
    private UbicacionParche ubicacion;

    public ParcheDTO(){

    }

    public ParcheDTO(String id, @NonNull String duenoDelParche, @NonNull NombreParche nombreParche,
                     DescripcionParche descripcion, FechaParche fechaCreacion, @NonNull FechaParche fechaInicio,
                     @NonNull FechaParche fechaFin, Estado estado, @NonNull Categoria categoria, @NonNull CapacidadParche capacidadMaxima,
                     @NonNull UbicacionParche ubicacion) {
        this.id = id;
        this.duenoDelParche = duenoDelParche;
        this.nombreParche = nombreParche;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.categoria = categoria;
        this.capacidadMaxima = capacidadMaxima;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getDuenoDelParche() {
        return duenoDelParche;
    }

    public void setDuenoDelParche(@NonNull String duenoDelParche) {
        this.duenoDelParche = duenoDelParche;
    }

    @NonNull
    public NombreParche getNombreParche() {
        return nombreParche;
    }

    public void setNombreParche(@NonNull NombreParche nombreParche) {
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

    @NonNull
    public FechaParche getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(@NonNull FechaParche fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @NonNull
    public FechaParche getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(@NonNull FechaParche fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @NonNull
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@NonNull Categoria categoria) {
        this.categoria = categoria;
    }

    @NonNull
    public CapacidadParche getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(@NonNull CapacidadParche capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @NonNull
    public UbicacionParche getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(@NonNull UbicacionParche ubicacion) {
        this.ubicacion = ubicacion;
    }
}
