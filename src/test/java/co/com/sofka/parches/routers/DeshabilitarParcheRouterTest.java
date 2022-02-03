package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.usecases.DeshabilitarParcheUseCase;
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
@ContextConfiguration(classes = {DeshabilitarParcheRouter.class, InscripcionRepository.class, DeshabilitarParcheUseCase.class, ParcheMapper.class})
class DeshabilitarParcheRouterTest {

    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private WebTestClient webTestClient;

    private static final String NOMBRE_PARCHE_DESHABILITAR_PARCHE_ROUTER = "parche de prueba";
    private static final String DESCRIPCION_DESHABILITAR_PARCHE_ROUTER = "descripcion de prueba";
    private static final String FECHA_CREACION_DESHABILITAR_PARCHE_ROUTER = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO_DESHABILITAR_PARCHE_ROUTER = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN_DESHABILITAR_PARCHE_ROUTER = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO_DESHABILITAR_PARCHE_ROUTER = Estado.DESHABILITADO;
    private static final Estado ESTADO_HABILITADO_DESHABILITAR_PARCHE_ROUTER = Estado.HABILITADO;
    private static final Categoria CATEGORIA_DESHABILITAR_PARCHE_ROUTER = Categoria.CONFERENCIAS;
    private static final Long CAPACIDAD_DESHABILITAR_PARCHE_ROUTER = 10L;
    private static final Double LAT_DESHABILITAR_PARCHE_ROUTER = 1.0;
    private static final Double LNG_DESHABILITAR_PARCHE_ROUTER = 1.0;
    private static final String DIRECCION_DESHABILITAR_PARCHE_ROUTER = "aaa";

    @Test
    void deshabilitarParcheRouterTest(){

        var parche = new Parche();
        parche.setId("1234");
        parche.setDuenoDelParche("SOY EL DUEÑO");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE_ROUTER));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE_ROUTER));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE_ROUTER));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE_ROUTER));
        parche.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE_ROUTER));
        parche.setEstado(ESTADO_DESHABILITAR_PARCHE_ROUTER);
        parche.setCategoria(CATEGORIA_DESHABILITAR_PARCHE_ROUTER);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE_ROUTER));
        parche.setUbicacion(new UbicacionParche(LAT_DESHABILITAR_PARCHE_ROUTER, LNG_DESHABILITAR_PARCHE_ROUTER, DIRECCION_DESHABILITAR_PARCHE_ROUTER));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("1234");
        parcheDTO.setDuenoDelParche("SOY EL DUEÑO");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setEstado(ESTADO_HABILITADO_DESHABILITAR_PARCHE_ROUTER);
        parcheDTO.setCategoria(CATEGORIA_DESHABILITAR_PARCHE_ROUTER);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE_ROUTER));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT_DESHABILITAR_PARCHE_ROUTER, LNG_DESHABILITAR_PARCHE_ROUTER, DIRECCION_DESHABILITAR_PARCHE_ROUTER));

        Mockito.when(parcheRepository.findById(parche.getId())).thenReturn(Mono.just(parche));
        Mockito.when(inscripcionRepository.deleteAllByParcheId(parche.getId())).thenReturn(Mono.empty());
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        webTestClient.put()
                .uri("/parches/deshabilitar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(parcheDTO), ParcheDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParcheDTO.class)
                .value(responseDeshabilitar ->{
                    Assertions.assertEquals(responseDeshabilitar.getId(), parche.getId());
                    Assertions.assertEquals(responseDeshabilitar.getDuenoDelParche(), parche.getDuenoDelParche());
                    Assertions.assertEquals(responseDeshabilitar.getNombreParche().getValorNombre(), parche.getNombreParche().getValorNombre());
                    Assertions.assertEquals(responseDeshabilitar.getDescripcion().getValorDescripcion(), parche.getDescripcion().getValorDescripcion());
                    Assertions.assertEquals(responseDeshabilitar.getFechaInicio().getValorFecha(), parche.getFechaInicio().getValorFecha());
                    Assertions.assertEquals(responseDeshabilitar.getFechaFin().getValorFecha(), parche.getFechaFin().getValorFecha());
                    Assertions.assertEquals(responseDeshabilitar.getEstado(), parche.getEstado());
                    Assertions.assertEquals(responseDeshabilitar.getCategoria(), parche.getCategoria());
                    Assertions.assertEquals(responseDeshabilitar.getCapacidadMaxima().getValorCapacidad(), parche.getCapacidadMaxima().getValorCapacidad());
                    Assertions.assertEquals(responseDeshabilitar.getUbicacionParche().getLat(), parche.getUbicacion().getLat());
                    Assertions.assertEquals(responseDeshabilitar.getUbicacionParche().getLng(), parche.getUbicacion().getLng());
                });

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteAllByParcheId(Mockito.any());

    }

}