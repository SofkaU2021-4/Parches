package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.useCases.ListarMisParchesCreadosUseCase;
import co.com.sofka.parches.valueObjects.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarMisParchesCreadosRouter.class, ListarMisParchesCreadosUseCase.class, ParcheMapper.class})
class ListarMisParchesCreadosRouterTest {

    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void listarMisParches() {

        Mockito.when(parcheRepository.findAllByDuenoDelParche(Mockito.any())).thenReturn(Flux.fromIterable(parches()));

        Mockito.when(inscripcionRepository.findAllByParcheId(Mockito.any())).thenReturn(Flux.fromIterable(inscripciones()));

        webTestClient.get()
                .uri("/duenoParche/misParches")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParcheDTO.class)
                .value(response -> {

                    Assertions.assertThat(response.get(0).getId()).isEqualTo("parche1Id");
                    Assertions.assertThat(response.get(0).getDuenoDelParche()).isEqualTo("duenoParche");
                    Assertions.assertThat(response.get(0).getNombreParche().getValorNombre()).isEqualTo("Parche 1");
                    Assertions.assertThat(response.get(0).getDescripcion().getValorDescripcion()).isEqualTo("Descripción parche 1");
                    Assertions.assertThat(response.get(0).getFechaCreacion().getValorFecha()).isEqualTo("2022-12-12T08:59:11.332");
                    Assertions.assertThat(response.get(0).getFechaInicio().getValorFecha()).isEqualTo("2022-12-12T10:59:11.332");
                    Assertions.assertThat(response.get(0).getFechaFin().getValorFecha()).isEqualTo("2022-12-12T12:59:11.332");
                    Assertions.assertThat(response.get(0).getEstado()).isEqualTo(Estado.HABILITADO);
                    Assertions.assertThat(response.get(0).getCategoria()).isEqualTo(Categoria.APRENDIZAJE);
                    Assertions.assertThat(response.get(0).getCapacidadMaxima().getValorCapacidad()).isEqualTo(10L);
                    Assertions.assertThat(response.get(0).getUbicacionParche().getLat()).isEqualTo(1.0);
                    Assertions.assertThat(response.get(0).getUbicacionParche().getLng()).isEqualTo(1.0);
                    Assertions.assertThat(response.get(0).getUbicacionParche().getFormatted()).isEqualTo("aaa");
                    Assertions.assertThat(response.get(0).getCantidadParticipantes().getValorCantidadParticipantes()).isEqualTo(3L);

                    Assertions.assertThat(response.get(1).getId()).isEqualTo("parche2Id");
                    Assertions.assertThat(response.get(1).getDuenoDelParche()).isEqualTo("duenoParche");
                    Assertions.assertThat(response.get(1).getNombreParche().getValorNombre()).isEqualTo("Parche 2");
                    Assertions.assertThat(response.get(1).getDescripcion().getValorDescripcion()).isEqualTo("Descripción parche 2");
                    Assertions.assertThat(response.get(1).getFechaCreacion().getValorFecha()).isEqualTo("2022-12-12T08:59:11.332");
                    Assertions.assertThat(response.get(1).getFechaInicio().getValorFecha()).isEqualTo("2022-12-12T10:59:11.332");
                    Assertions.assertThat(response.get(1).getFechaFin().getValorFecha()).isEqualTo("2022-12-12T12:59:11.332");
                    Assertions.assertThat(response.get(1).getEstado()).isEqualTo(Estado.HABILITADO);
                    Assertions.assertThat(response.get(1).getCategoria()).isEqualTo(Categoria.APRENDIZAJE);
                    Assertions.assertThat(response.get(1).getCapacidadMaxima().getValorCapacidad()).isEqualTo(10L);
                    Assertions.assertThat(response.get(1).getUbicacionParche().getLat()).isEqualTo(1.0);
                    Assertions.assertThat(response.get(1).getUbicacionParche().getLng()).isEqualTo(1.0);
                    Assertions.assertThat(response.get(1).getUbicacionParche().getFormatted()).isEqualTo("aaa");
                    Assertions.assertThat(response.get(1).getCantidadParticipantes().getValorCantidadParticipantes()).isEqualTo(3L);

                });

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