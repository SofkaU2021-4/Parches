package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.useCases.ListarParchesUseCase;
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

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarParchesRouter.class, ListarParchesUseCase.class, ParcheMapper.class})
class ListarParchesRouterTest {
    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetParches(){

        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("Dueño del Parche");
        parche.setNombreParche(new NombreParche("Nombre Parche"));
        parche.setDescripcion(new DescripcionParche("Descripcion parche"));
        parche.setFechaCreacion(new FechaParche("2022-01-31T17:07:35.361"));
        parche.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche.setEstado(Estado.HABILITADO);
        parche.setCategoria(Categoria.APRENDIZAJE);
        parche.setCapacidadMaxima(new CapacidadParche(50L));
        parche.setUbicacion(new UbicacionParche(1D,1D, ""));


        var parche1 = new Parche();
        parche1.setId("xxxx-xxxx");
        parche1.setDuenoDelParche("Dueño del Parche numero 1");
        parche1.setNombreParche(new NombreParche("Nombre Parche numero 1"));
        parche1.setDescripcion(new DescripcionParche("Descripcion parche numero 1"));
        parche1.setFechaCreacion(new FechaParche("2022-01-31T17:07:35.361"));
        parche1.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche1.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche1.setEstado(Estado.DESHABILITADO);
        parche1.setCategoria(Categoria.GASTRONOMIA);
        parche1.setCapacidadMaxima(new CapacidadParche(250L));
        parche1.setUbicacion(new UbicacionParche(45D,90D, ""));

        var inscripcion = new Inscripcion();
        inscripcion.setId("inscritoxxxx");
        inscripcion.setParcheId("xxxx");

        var inscripcion1 = new Inscripcion();
        inscripcion1.setId("inscritoxxxx1");
        inscripcion1.setParcheId("xxxx-xxxx");

        var inscripcion2 = new Inscripcion();
        inscripcion2.setId("inscritoxxxx2");
        inscripcion2.setParcheId("xxxx-xxxx");

        Mockito.when(parcheRepository.findAll()).thenReturn(Flux.just(parche, parche1));
        Mockito.when(inscripcionRepository.findAllByParcheId("xxxx")).thenReturn(Flux.just(inscripcion));
        Mockito.when(inscripcionRepository.findAllByParcheId("xxxx-xxxx")).thenReturn(Flux.just(inscripcion1, inscripcion2));

        webTestClient.get()
                .uri("/parches")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParcheDTO.class)
                .value(parcheResponse -> {
                    Assertions.assertThat(parcheResponse.get(0).getNombreParche().getValorNombre()).isEqualTo("Nombre Parche");
                    Assertions.assertThat(parcheResponse.get(0).getId()).isEqualTo("xxxx");
                    Assertions.assertThat(parcheResponse.get(0).getDuenoDelParche()).isEqualTo("Dueño del Parche");
                    Assertions.assertThat(parcheResponse.get(0).getDescripcion().getValorDescripcion()).isEqualTo("Descripcion parche");
                    Assertions.assertThat(parcheResponse.get(0).getFechaCreacion().getValorFecha()).isEqualTo("2022-01-31T17:07:35.361");
                    Assertions.assertThat(parcheResponse.get(0).getFechaInicio().getValorFecha()).isEqualTo("2022-12-12T10:59:11.332");
                    Assertions.assertThat(parcheResponse.get(0).getFechaFin().getValorFecha()).isEqualTo("2022-12-12T12:59:11.332");
                    Assertions.assertThat(parcheResponse.get(0).getEstado()).isEqualTo(Estado.HABILITADO);
                    Assertions.assertThat(parcheResponse.get(0).getCategoria()).isEqualTo(Categoria.APRENDIZAJE);
                    Assertions.assertThat(parcheResponse.get(0).getCapacidadMaxima().getValorCapacidad()).isEqualTo(50L);
                    Assertions.assertThat(parcheResponse.get(0).getUbicacionParche().getLat()).isEqualTo(1D);
                    Assertions.assertThat(parcheResponse.get(0).getUbicacionParche().getLng()).isEqualTo(1D);
                    Assertions.assertThat(parcheResponse.get(0).getCantidadParticipantes().getValorCantidadParticipantes()).isEqualTo(1L);

                    Assertions.assertThat(parcheResponse.get(1).getNombreParche().getValorNombre()).isEqualTo("Nombre Parche numero 1");
                    Assertions.assertThat(parcheResponse.get(1).getId()).isEqualTo("xxxx-xxxx");
                    Assertions.assertThat(parcheResponse.get(1).getDuenoDelParche()).isEqualTo("Dueño del Parche numero 1");
                    Assertions.assertThat(parcheResponse.get(1).getDescripcion().getValorDescripcion()).isEqualTo("Descripcion parche numero 1");
                    Assertions.assertThat(parcheResponse.get(1).getFechaCreacion().getValorFecha()).isEqualTo("2022-01-31T17:07:35.361");
                    Assertions.assertThat(parcheResponse.get(1).getFechaInicio().getValorFecha()).isEqualTo("2022-12-12T10:59:11.332");
                    Assertions.assertThat(parcheResponse.get(1).getFechaFin().getValorFecha()).isEqualTo("2022-12-12T12:59:11.332");
                    Assertions.assertThat(parcheResponse.get(1).getEstado()).isEqualTo(Estado.DESHABILITADO);
                    Assertions.assertThat(parcheResponse.get(1).getCategoria()).isEqualTo(Categoria.GASTRONOMIA);
                    Assertions.assertThat(parcheResponse.get(1).getCapacidadMaxima().getValorCapacidad()).isEqualTo(250L);
                    Assertions.assertThat(parcheResponse.get(1).getUbicacionParche().getLat()).isEqualTo(45D);
                    Assertions.assertThat(parcheResponse.get(1).getUbicacionParche().getLng()).isEqualTo(90D);
                    Assertions.assertThat(parcheResponse.get(1).getCantidadParticipantes().getValorCantidadParticipantes()).isEqualTo(2L);
                });
    }
}