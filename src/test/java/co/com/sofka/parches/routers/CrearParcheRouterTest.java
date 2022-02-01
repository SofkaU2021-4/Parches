package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.useCases.CrearParcheUseCase;
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

    @Test
    void crearParcheRouterTest(){

        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche("parche de prueba"));
        parche.setDescripcion(new DescripcionParche("descripcion de prueba"));
        parche.setFechaCreacion(new FechaParche("2022-12-12T08:59:11.332"));
        parche.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche.setEstado(Estado.HABILITADO);
        parche.setCategoria(Categoria.APRENDIZAJE);
        parche.setCapacidadMaxima(new CapacidadParche(10L));
        parche.setUbicacion(new UbicacionParche(1.0, 1.0, "aaa"));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche("parche de prueba"));
        parcheDTO.setDescripcion(new DescripcionParche("descripcion de prueba"));
        parcheDTO.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parcheDTO.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parcheDTO.setEstado(Estado.HABILITADO);
        parcheDTO.setCategoria(Categoria.APRENDIZAJE);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(10L));
        parcheDTO.setUbicacionParche(new UbicacionParche(1.0, 1.0, "aaa"));

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