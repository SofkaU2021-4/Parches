package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.repositories.UsuarioRepository;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class VerDetalleParcheUseCaseTest {

    ParcheRepository parcheRepository;
    InscripcionRepository inscripcionRepository;
    ParcheMapper parcheMapper;
    ComentarioRepository comentarioRepository;
    UsuarioRepository usuarioRepository;
    ComentarioMapper comentarioMapper;
    MapperUtils usuarioMapper;
    VerDetalleParcheUseCase verDetalleParcheUseCase;

    @BeforeEach
    void setup() {
        parcheMapper = new ParcheMapper();
        comentarioMapper = new ComentarioMapper();
        usuarioMapper = new MapperUtils();
        parcheRepository = mock(ParcheRepository.class);
        inscripcionRepository = mock(InscripcionRepository.class);
        comentarioRepository = mock(ComentarioRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        verDetalleParcheUseCase = new VerDetalleParcheUseCase(
                parcheRepository,
                parcheMapper,
                inscripcionRepository,
                comentarioRepository,
                usuarioRepository,
                comentarioMapper,
                usuarioMapper
        );
    }

    @Test
    void VerDetalleParcheUseCase() {

        when(parcheRepository.findById(parcheId())).thenReturn(Mono.just(parche()));
        when(usuarioRepository.findByUid(duenoDelParche())).thenReturn(Mono.just(usuarioDuenoParche()));
        when(usuarioRepository.findById("usuario02")).thenReturn(Mono.just(usuarioPrueba1()));
        when(usuarioRepository.findById("usuario03")).thenReturn(Mono.just(usuarioPrueba2()));
        when(comentarioRepository.findByParcheId(parcheId())).thenReturn(Flux.fromIterable(listaComentarios()));
        when(inscripcionRepository.findAllByParcheId(parcheId())).thenReturn(Flux.just(inscripcion()));
        when(inscripcionRepository.findByParcheIdAndUsuarioId(any(),any())).thenReturn(Mono.just(inscripcion()));

        StepVerifier.create(verDetalleParcheUseCase.verDetalleParche(parcheId(),"uia002"))
                .expectNextMatches(detallesParcheDTO -> {
                    assert detallesParcheDTO.getId().equals("parcheID000999");
                    assert detallesParcheDTO.getDuenoDelParche().getId().equals("usuario01");
                    assert detallesParcheDTO.getDuenoDelParche().getUid().equals("User00056");
                    assert detallesParcheDTO.getDuenoDelParche().getNombres().equals("Juan rodriguez");
                    assert detallesParcheDTO.getDuenoDelParche().getEmail().equals("jrod@correo.com");
                    assert detallesParcheDTO.getDuenoDelParche().getImagenUrl().equals("imagen/url/dueno");
                    assert detallesParcheDTO.getNombreParche().getValorNombre().equals("concierto de abril");
                    assert detallesParcheDTO.getDescripcion().getValorDescripcion().equals("Parche para los que van a ir al concierto");
                    assert detallesParcheDTO.getFechaDeCreacion().getValorFecha().toString().equals("2022-02-01T17:54:47.450");
                    assert detallesParcheDTO.getFechaDeInicio().getValorFecha().toString().equals("2022-02-01T21:06:42.140");
                    assert detallesParcheDTO.getFechaFin().getValorFecha().toString().equals("2022-02-02T21:06:42.140");
                    assert detallesParcheDTO.getCategoria().equals(Categoria.FIESTAS);
                    assert detallesParcheDTO.getCapacidadMaxima().getValorCapacidad().equals(100L);
                    assert detallesParcheDTO.getUbicacionParche().getLat().equals(2.2);
                    assert detallesParcheDTO.getUbicacionParche().getLng().equals(2.2);
                    assert detallesParcheDTO.getCantidadAsistentes().equals(1);
                    assert detallesParcheDTO.getInscripcion().getParcheId().equals("parcheID000999");
                    assert detallesParcheDTO.getInscripcion().getFechaDeInscripcion().getValorFecha().toString().equals("2022-02-01T21:06:42.140");
                    assert detallesParcheDTO.getInscripcion().getId().equals("inscripcion0001");
                    assert detallesParcheDTO.getInscripcion().getUsuarioId().equals("uia002");
                    assert detallesParcheDTO.getComentarioDTOS().get(0).getComentario().equals("comentario numero 1");
                    assert detallesParcheDTO.getComentarioDTOS().get(1).getComentario().equals("comentario numero 2");
                    return true;
                })
                .verifyComplete();
        verify(parcheRepository).findById(parcheId());
        verify(comentarioRepository).findByParcheId(any());
        verify(inscripcionRepository).findAllByParcheId(any());
        verify(inscripcionRepository).findByParcheIdAndUsuarioId(any(),any());
        Mockito.verify(usuarioRepository, Mockito.times(1)).findByUid(duenoDelParche());
        Mockito.verify(usuarioRepository, Mockito.times(2)).findById((String) any());
    }

    private Parche parche() {
        var parche = new Parche();
        parche.setId(parcheId());
        parche.setDuenoDelParche(duenoDelParche());
        parche.setNombreParche(nombreParche());
        parche.setDescripcion(descripcionParche());
        parche.setFechaCreacion(fechaCreacion());
        parche.setFechaInicio(fechaInicio());
        parche.setFechaFin(fechaFin());
        parche.setEstado(estado());
        parche.setCategoria(categoria());
        parche.setCapacidadMaxima(capacidadParche());
        parche.setUbicacion(ubicacionParche());
        return parche;
    }

    //INSCRIPCIONES
    private Inscripcion inscripcion() {
        return new Inscripcion(
                "inscripcion0001",
                parcheId(),
                "uia002",
                fechaInicio());
    }

    //COMENTARIOS
    private Comentario comentario1() {
        return new Comentario(
                "comentario001",
                "usuario02",
                parcheId(),
                "comentario numero 1"
        );
    }

    private Comentario comentario2() {
        return new Comentario(
                "comentario002",
                "usuario03",
                parcheId(),
                "comentario numero 2"
        );
    }

    //ARRAY DE COMENTARIOS
    private List<Comentario> listaComentarios(){
        List<Comentario> lista = new ArrayList<>();
        lista.add(comentario1());
        lista.add(comentario2());
        return lista;
    }

    //USUARIOS
    private Usuario usuarioDuenoParche() {
        return new Usuario(
                "usuario01",
                duenoDelParche(),
                "Juan rodriguez",
                "jrod@correo.com",
                "imagen/url/dueno");
    }

    private Usuario usuarioPrueba1() {
        return new Usuario(
                "usuario02",
                "uia002",
                "Juan rodriguez",
                "usuarioprueba1@correo.com",
                "imagen/url");
    }

    private Usuario usuarioPrueba2() {
        return new Usuario(
                "usuario03",
                "uid003",
                "Juan rodriguez",
                "usuarioprueba2@correo.com",
                "imagen/url");
    }


    //VALUE OBJECTS ***************************
    private String parcheId() {
        return "parcheID000999";
    }

    private String duenoDelParche() {
        return "User00056";
    }

    private NombreParche nombreParche() {
        return new NombreParche("concierto de abril");
    }

    private DescripcionParche descripcionParche() {
        return new DescripcionParche("Parche para los que van a ir al concierto");
    }

    private FechaParche fechaCreacion() {
        return new FechaParche("2022-02-01T17:54:47.45");
    }

    private FechaParche fechaInicio() {
        return new FechaParche("2022-02-01T21:06:42.14");
    }

    private FechaParche fechaFin() {
        return new FechaParche("2022-02-02T21:06:42.14");
    }

    private Estado estado() {
        return Estado.HABILITADO;
    }

    private Categoria categoria() {
        return Categoria.FIESTAS;
    }

    private CapacidadParche capacidadParche() {
        return new CapacidadParche(100L);
    }

    private UbicacionParche ubicacionParche() {
        return new UbicacionParche(2.2, 2.2, null);
    }

}