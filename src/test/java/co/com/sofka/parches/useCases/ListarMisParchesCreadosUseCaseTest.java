package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListarMisParchesCreadosUseCaseTest {

    ParcheRepository parcheRepository;
    ParcheMapper parcheMapper;
    InscripcionRepository inscripcionRepository;
    ListarMisParchesCreadosUseCase listarMisParchesCreadosUseCase;

    @BeforeEach
    public void setup() {
        parcheMapper = new ParcheMapper();
        parcheRepository = Mockito.mock(ParcheRepository.class);
        inscripcionRepository = Mockito.mock(InscripcionRepository.class);
        listarMisParchesCreadosUseCase = new ListarMisParchesCreadosUseCase(parcheRepository, parcheMapper, inscripcionRepository);
    }

    @Test
    void listarMisParches() {

        Mockito.when(parcheRepository.findAllByDuenoDelParche(Mockito.any())).thenReturn(Flux.fromIterable(parches()));

        Mockito.when(inscripcionRepository.findAllByParcheId(Mockito.any())).thenReturn(Flux.empty());

        StepVerifier.create(listarMisParchesCreadosUseCase.listarMisParchesCreados("parche1Id"))
                .expectNextMatches(parcheDTO -> {

                    assert parcheDTO.getId().equals("parche1Id");
                    assert parcheDTO.getDuenoDelParche().equals("duenoParche");
                    assert parcheDTO.getNombreParche().getValorNombre().equals("Parche 1");
                    assert parcheDTO.getDescripcion().getValorDescripcion().equals("Descripción parche 1");
                    assert parcheDTO.getFechaCreacion().getValorFecha().toString().equals("2022-12-12T08:59:11.332");
                    assert parcheDTO.getFechaInicio().getValorFecha().toString().equals("2022-12-12T10:59:11.332");
                    assert parcheDTO.getFechaFin().getValorFecha().toString().equals("2022-12-12T12:59:11.332");
                    assert parcheDTO.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getLng().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getFormatted().equals("aaa");
                    assert parcheDTO.getCantidadParticipantes().getValorCantidadParticipantes().equals(0L);


                    return true;
                }).expectNextMatches(parcheDTO -> {

                    assert parcheDTO.getId().equals("parche2Id");
                    assert parcheDTO.getDuenoDelParche().equals("duenoParche");
                    assert parcheDTO.getNombreParche().getValorNombre().equals("Parche 2");
                    assert parcheDTO.getDescripcion().getValorDescripcion().equals("Descripción parche 2");
                    assert parcheDTO.getFechaCreacion().getValorFecha().toString().equals("2022-12-12T08:59:11.332");
                    assert parcheDTO.getFechaInicio().getValorFecha().toString().equals("2022-12-12T10:59:11.332");
                    assert parcheDTO.getFechaFin().getValorFecha().toString().equals("2022-12-12T12:59:11.332");
                    assert parcheDTO.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getLng().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getFormatted().equals("aaa");
                    assert parcheDTO.getCantidadParticipantes().getValorCantidadParticipantes().equals(0L);

                    return true;
                }).verifyComplete();

        Mockito.verify(parcheRepository).findAllByDuenoDelParche(Mockito.any());
        Mockito.verify(inscripcionRepository, Mockito.times(2)).findAllByParcheId(Mockito.any());

    }

    @Test
    void listarMisParchesConParticipantes() {

        Mockito.when(parcheRepository.findAllByDuenoDelParche(Mockito.any())).thenReturn(Flux.fromIterable(parches()));

        Mockito.when(inscripcionRepository.findAllByParcheId(Mockito.any())).thenReturn(Flux.fromIterable(inscripciones()));

        StepVerifier.create(listarMisParchesCreadosUseCase.listarMisParchesCreados("parche1Id"))
                .expectNextMatches(parcheDTO -> {

                    assert parcheDTO.getId().equals("parche1Id");
                    assert parcheDTO.getDuenoDelParche().equals("duenoParche");
                    assert parcheDTO.getNombreParche().getValorNombre().equals("Parche 1");
                    assert parcheDTO.getDescripcion().getValorDescripcion().equals("Descripción parche 1");
                    assert parcheDTO.getFechaCreacion().getValorFecha().toString().equals("2022-12-12T08:59:11.332");
                    assert parcheDTO.getFechaInicio().getValorFecha().toString().equals("2022-12-12T10:59:11.332");
                    assert parcheDTO.getFechaFin().getValorFecha().toString().equals("2022-12-12T12:59:11.332");
                    assert parcheDTO.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getLng().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getFormatted().equals("aaa");
                    assert parcheDTO.getCantidadParticipantes().getValorCantidadParticipantes().equals(3L);


                    return true;
                }).expectNextMatches(parcheDTO -> {

                    assert parcheDTO.getId().equals("parche2Id");
                    assert parcheDTO.getDuenoDelParche().equals("duenoParche");
                    assert parcheDTO.getNombreParche().getValorNombre().equals("Parche 2");
                    assert parcheDTO.getDescripcion().getValorDescripcion().equals("Descripción parche 2");
                    assert parcheDTO.getFechaCreacion().getValorFecha().toString().equals("2022-12-12T08:59:11.332");
                    assert parcheDTO.getFechaInicio().getValorFecha().toString().equals("2022-12-12T10:59:11.332");
                    assert parcheDTO.getFechaFin().getValorFecha().toString().equals("2022-12-12T12:59:11.332");
                    assert parcheDTO.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getLng().equals(1.0);
                    assert parcheDTO.getUbicacionParche().getFormatted().equals("aaa");
                    assert parcheDTO.getCantidadParticipantes().getValorCantidadParticipantes().equals(3L);

                    return true;
                }).verifyComplete();

        Mockito.verify(parcheRepository).findAllByDuenoDelParche(Mockito.any());
        Mockito.verify(inscripcionRepository, Mockito.times(2)).findAllByParcheId(Mockito.any());

    }

    private List<Parche> parches() {

        Parche parche1 = new Parche();
        parche1.setId("parche1Id");
        parche1.setDuenoDelParche("duenoParche");
        parche1.setNombreParche(new NombreParche("Parche 1"));
        parche1.setDescripcion(new DescripcionParche("Descripción parche 1"));
        parche1.setFechaCreacion(new FechaParche("2022-12-12T08:59:11.332"));
        parche1.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche1.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche1.setEstado(Estado.HABILITADO);
        parche1.setCategoria(Categoria.APRENDIZAJE);
        parche1.setCapacidadMaxima(new CapacidadParche(10L));
        parche1.setUbicacion(new UbicacionParche(1.0, 1.0, "aaa"));

        Parche parche2 = new Parche();
        parche2.setId("parche2Id");
        parche2.setDuenoDelParche("duenoParche");
        parche2.setNombreParche(new NombreParche("Parche 2"));
        parche2.setDescripcion(new DescripcionParche("Descripción parche 2"));
        parche2.setFechaCreacion(new FechaParche("2022-12-12T08:59:11.332"));
        parche2.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche2.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche2.setEstado(Estado.HABILITADO);
        parche2.setCategoria(Categoria.APRENDIZAJE);
        parche2.setCapacidadMaxima(new CapacidadParche(10L));
        parche2.setUbicacion(new UbicacionParche(1.0, 1.0, "aaa"));

        List<Parche> parches = new ArrayList<>();
        parches.add(parche1);
        parches.add(parche2);
        return parches;
    }

    private List<Inscripcion> inscripciones() {

        Inscripcion inscripcion1 = new Inscripcion();
        inscripcion1.setId("inscripcion1Id");
        inscripcion1.setParcheId("parche1Id");
        inscripcion1.setUsuarioId("duenoParche");
        inscripcion1.setFechaDeInscripcion(new FechaParche("2022-12-12T12:59:11.332"));

        Inscripcion inscripcion2 = new Inscripcion();
        inscripcion2.setId("inscripcion2Id");
        inscripcion2.setParcheId("parche1Id");
        inscripcion2.setUsuarioId("XXXX");
        inscripcion2.setFechaDeInscripcion(new FechaParche("2022-12-12T12:59:11.332"));

        Inscripcion inscripcion3 = new Inscripcion();
        inscripcion3.setId("inscripcion3Id");
        inscripcion3.setParcheId("parche1Id");
        inscripcion3.setUsuarioId("YYYY");
        inscripcion3.setFechaDeInscripcion(new FechaParche("2022-12-12T12:59:11.332"));

        List<Inscripcion> inscripciones = new ArrayList<>();
        inscripciones.add(inscripcion1);
        inscripciones.add(inscripcion2);
        inscripciones.add(inscripcion3);

        return inscripciones;
    }

}