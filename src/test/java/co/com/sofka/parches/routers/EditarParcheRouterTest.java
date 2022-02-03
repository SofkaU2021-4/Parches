package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.usecases.EditarParcheUseCase;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EditarParcheRouter.class, InscripcionRepository.class, EditarParcheUseCase.class, ParcheMapper.class})
class EditarParcheRouterTest {

    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private WebTestClient webTestClient;

    private static final String NOMBRE_PARCHE_ROUTER = "parche de prueba";
    private static final String DESCRIPCION_ROUTER = "descripcion de prueba";
    private static final String FECHA_CREACION_ROUTER = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO_ROUTER = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN_ROUTER = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO_ROUTER = Estado.HABILITADO;
    private static final Categoria CATEGORIA_ROUTER = Categoria.APRENDIZAJE;
    private static final Long CAPACIDAD_ROUTER = 10L;
    private static final Double LAT_ROUTER = 1.0;
    private static final Double LNG_ROUTER = 1.0;
    private static final String DIRECCION_ROUTER = "aaa";

    @Test
    void editarParcheRouterTest(){

        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE_ROUTER));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION_ROUTER));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION_ROUTER));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO_ROUTER));
        parche.setFechaFin(new FechaParche(FECHA_FIN_ROUTER));
        parche.setEstado(ESTADO_ROUTER);
        parche.setCategoria(CATEGORIA_ROUTER);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_ROUTER));
        parche.setUbicacion(new UbicacionParche(LAT_ROUTER, LNG_ROUTER, DIRECCION_ROUTER));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE_ROUTER));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION_ROUTER));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION_ROUTER));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO_ROUTER));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN_ROUTER));
        parcheDTO.setEstado(ESTADO_ROUTER);
        parcheDTO.setCategoria(CATEGORIA_ROUTER);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_ROUTER));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT_ROUTER, LNG_ROUTER, DIRECCION_ROUTER));

        Mockito.when(parcheRepository.findById(parche.getId())).thenReturn(Mono.just(parche));
        Mockito.when(inscripcionRepository.countAllByParcheId(parche.getId())).thenReturn(Mono.just(2L));
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        webTestClient.put()
                .uri("/parches/editar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(parcheDTO), ParcheDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParcheDTO.class)
                .value(responseEditar ->{
                    Assertions.assertEquals(responseEditar.getId(), parche.getId());
                    Assertions.assertEquals(responseEditar.getDuenoDelParche(), parche.getDuenoDelParche());
                    Assertions.assertEquals(responseEditar.getNombreParche().getValorNombre(), parche.getNombreParche().getValorNombre());
                    Assertions.assertEquals(responseEditar.getDescripcion().getValorDescripcion(), parche.getDescripcion().getValorDescripcion());
                    Assertions.assertEquals(responseEditar.getFechaInicio().getValorFecha(), parche.getFechaInicio().getValorFecha());
                    Assertions.assertEquals(responseEditar.getFechaFin().getValorFecha(), parche.getFechaFin().getValorFecha());
                    Assertions.assertEquals(responseEditar.getEstado(), parche.getEstado());
                    Assertions.assertEquals(responseEditar.getCategoria(), parche.getCategoria());
                    Assertions.assertEquals(responseEditar.getCapacidadMaxima().getValorCapacidad(), parche.getCapacidadMaxima().getValorCapacidad());
                    Assertions.assertEquals(responseEditar.getUbicacionParche().getLat(), parche.getUbicacion().getLat());
                    Assertions.assertEquals(responseEditar.getUbicacionParche().getLng(), parche.getUbicacion().getLng());
                });

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());

    }

}