package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.usecases.CrearParcheUseCase;
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
@ContextConfiguration(classes = {CrearParcheRouter.class, CrearParcheUseCase.class, ParcheMapper.class})
class CrearParcheRouterTest {

    @MockBean
    private ParcheRepository parcheRepository;

    @Autowired
    private WebTestClient webTestClient;

    private static final String NOMBRE_PARCHE_CREAR_PARCHE_ROUTER = "parche de prueba";
    private static final String DESCRIPCION_CREAR_PARCHE_ROUTER = "descripcion de prueba";
    private static final String FECHA_CREACION_CREAR_PARCHE_ROUTER = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO_CREAR_PARCHE_ROUTER = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN_CREAR_PARCHE_ROUTER = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO_CREAR_PARCHE_ROUTER = Estado.HABILITADO;
    private static final Categoria CATEGORIA_CREAR_PARCHE_ROUTER = Categoria.APRENDIZAJE;
    private static final Long CAPACIDAD_CREAR_PARCHE_ROUTER = 10L;
    private static final Double LAT_CREAR_PARCHE_ROUTER = 1.0;
    private static final Double LNG_CREAR_PARCHE_ROUTER = 1.0;
    private static final String DIRECCION_CREAR_PARCHE_ROUTER = "aaa";

    @Test
    void crearParcheRouterTest(){

        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE_CREAR_PARCHE_ROUTER));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION_CREAR_PARCHE_ROUTER));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION_CREAR_PARCHE_ROUTER));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO_CREAR_PARCHE_ROUTER));
        parche.setFechaFin(new FechaParche(FECHA_FIN_CREAR_PARCHE_ROUTER));
        parche.setEstado(ESTADO_CREAR_PARCHE_ROUTER);
        parche.setCategoria(CATEGORIA_CREAR_PARCHE_ROUTER);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_CREAR_PARCHE_ROUTER));
        parche.setUbicacion(new UbicacionParche(LAT_CREAR_PARCHE_ROUTER, LNG_CREAR_PARCHE_ROUTER, DIRECCION_CREAR_PARCHE_ROUTER));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE_CREAR_PARCHE_ROUTER));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION_CREAR_PARCHE_ROUTER));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION_CREAR_PARCHE_ROUTER));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO_CREAR_PARCHE_ROUTER));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN_CREAR_PARCHE_ROUTER));
        parcheDTO.setEstado(ESTADO_CREAR_PARCHE_ROUTER);
        parcheDTO.setCategoria(CATEGORIA_CREAR_PARCHE_ROUTER);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_CREAR_PARCHE_ROUTER));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT_CREAR_PARCHE_ROUTER, LNG_CREAR_PARCHE_ROUTER, DIRECCION_CREAR_PARCHE_ROUTER));

        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        webTestClient.post()
                .uri("/parches/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(parcheDTO), ParcheDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ParcheDTO.class)
                .value(response ->{
                    Assertions.assertEquals(response.getId(), parche.getId());
                    Assertions.assertEquals(response.getDuenoDelParche(), parche.getDuenoDelParche());
                    Assertions.assertEquals(response.getNombreParche().getValorNombre(), parche.getNombreParche().getValorNombre());
                    Assertions.assertEquals(response.getDescripcion().getValorDescripcion(), parche.getDescripcion().getValorDescripcion());
                    Assertions.assertEquals(response.getFechaInicio().getValorFecha(), parche.getFechaInicio().getValorFecha());
                    Assertions.assertEquals(response.getFechaFin().getValorFecha(), parche.getFechaFin().getValorFecha());
                    Assertions.assertEquals(response.getEstado(), parche.getEstado());
                    Assertions.assertEquals(response.getCategoria(), parche.getCategoria());
                    Assertions.assertEquals(response.getCapacidadMaxima().getValorCapacidad(), parche.getCapacidadMaxima().getValorCapacidad());
                    Assertions.assertEquals(response.getUbicacionParche().getLat(), parche.getUbicacion().getLat());
                    Assertions.assertEquals(response.getUbicacionParche().getLng(), parche.getUbicacion().getLng());
                });

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());

    }

}