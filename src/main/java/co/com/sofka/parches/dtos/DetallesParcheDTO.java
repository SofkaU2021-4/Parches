package co.com.sofka.parches.dtos;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.valueObjects.*;

import java.util.List;

public class DetallesParcheDTO {

    private String id;
    private UsuarioDTO duenoDelParche;
    private NombreParche nombreParche;
    private DescripcionParche descripcion;
    private FechaParche fechaDeCreacion;
    private FechaParche fechaDeInicio;
    private FechaParche fechaFin;
    private Categoria categoria;
    private CapacidadParche capacidadMaxima;
    private UbicacionParche ubicacionParche;
    private Integer cantidadAsistentes;
    private Inscripcion inscripcion;
    private List<ComentarioDTO> comentarioDTOS;

    public DetallesParcheDTO() {
    }

    public DetallesParcheDTO(String id,
                             NombreParche nombreParche,
                             DescripcionParche descripcion,
                             FechaParche fechaDeCreacion,
                             FechaParche fechaDeInicio,
                             FechaParche fechaFin,
                             Categoria categoria,
                             CapacidadParche capacidadMaxima,
                             UbicacionParche ubicacionParche) {
        this.id = id;
        this.nombreParche = nombreParche;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaFin = fechaFin;
        this.categoria = categoria;
        this.capacidadMaxima = capacidadMaxima;
        this.ubicacionParche = ubicacionParche;
    }

    public List<ComentarioDTO> getComentarioDTOS() {
        return comentarioDTOS;
    }

    public void setComentarioDTOS(List<ComentarioDTO> comentarioDTOS) {
        this.comentarioDTOS = comentarioDTOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioDTO getDuenoDelParche() {
        return duenoDelParche;
    }

    public void setDuenoDelParche(UsuarioDTO duenoDelParche) {
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

    public FechaParche getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(FechaParche fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public FechaParche getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(FechaParche fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public FechaParche getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(FechaParche fechaFin) {
        this.fechaFin = fechaFin;
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

    public Integer getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(Integer cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }


    @Override
    public String toString() {
        return "DetallesParcheDTO{" +
                "id='" + id + '\'' +
                ", duenoDelParche='" + duenoDelParche + '\'' +
                ", nombreParche=" + nombreParche +
                ", descripcion=" + descripcion +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", fechaDeInicio=" + fechaDeInicio +
                ", fechaFin=" + fechaFin +
                ", categoria=" + categoria +
                ", capacidadMaxima=" + capacidadMaxima +
                ", ubicacionParche=" + ubicacionParche +
                ", cantidadAsistentes=" + cantidadAsistentes +
                ", inscripcion=" + inscripcion +
                '}';
    }
}
