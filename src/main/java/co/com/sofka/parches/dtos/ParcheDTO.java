package co.com.sofka.parches.dtos;

import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.valueObjects.*;
import com.mongodb.lang.NonNull;

import java.util.Optional;

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
    private UbicacionParche ubicacionParche;
    private CantidadParticipantes cantidadParticipantes;
    public ParcheDTO(){

    }

    public ParcheDTO(String id,
                     @NonNull String duenoDelParche,
                     @NonNull String nombreParche,
                     String descripcion,
                     String fechaCreacion,
                     @NonNull String fechaInicio,
                     @NonNull String fechaFin,
                     Estado estado,
                     @NonNull Categoria categoria,
                     @NonNull Long capacidadMaxima,
                     @NonNull UbicacionParche ubicacionParche) {
        this.id = id;
        this.duenoDelParche = duenoDelParche;
        this.nombreParche = new NombreParche(nombreParche);
        this.descripcion = new DescripcionParche(descripcion);
        this.fechaCreacion = new FechaParche(fechaCreacion);
        this.fechaInicio = new FechaParche(fechaInicio);
        this.fechaFin = new FechaParche(fechaFin);
        this.estado = estado;
        this.categoria = categoria;
        this.capacidadMaxima = new CapacidadParche(capacidadMaxima);
        this.ubicacionParche = ubicacionParche;
    }

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

    public UbicacionParche getUbicacionParche() {
        return ubicacionParche;
    }

    public void setUbicacionParche(UbicacionParche ubicacionParche) {
        this.ubicacionParche = ubicacionParche;
    }

    public void setCantidadParticipantes(CantidadParticipantes cantidadParticipantes) {
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public CantidadParticipantes getCantidadParticipantes() {
        return cantidadParticipantes;
    }

    @Override
    public String toString() {
        return "ParcheDTO{" +
                "id='" + id + '\'' +
                ", duenoDelParche='" + duenoDelParche + '\'' +
                ", nombreParche=" + nombreParche +
                ", descripcion=" + descripcion +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", categoria=" + categoria +
                ", capacidadMaxima=" + capacidadMaxima +
                ", ubicacionParche=" + ubicacionParche +
                ", cantidadParticipantes=" + cantidadParticipantes +
                '}';
    }
}
