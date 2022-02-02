package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.useCases.EliminarParcheUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarParcheRouter.class, InscripcionRepository.class, EliminarParcheUseCase.class, ParcheMapper.class})

class EliminarParcheRouterTest {

    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @MockBean
    private ComentarioRepository comentarioRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void eliminarParcheRouterTest(){

        var parche = new Parche();
        parche.setId("xxxx");

        Mockito.when(inscripcionRepository.deleteAllByParcheId(parche.getId())).thenReturn(Mono.empty());
        Mockito.when(comentarioRepository.deleteAllByParcheId(parche.getId())).thenReturn(Mono.empty());
        Mockito.when(parcheRepository.deleteById(parche.getId())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/parches/eliminar/xxxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteAllByParcheId(parche.getId());
        Mockito.verify(comentarioRepository, Mockito.times(1)).deleteAllByParcheId(parche.getId());
        Mockito.verify(parcheRepository, Mockito.times(1)).deleteById(parche.getId());

    }

    @Test
    void eliminarParcheRouterTestError(){

        Mockito.when(inscripcionRepository.deleteAllByParcheId(Mockito.any())).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        Mockito.when(comentarioRepository.deleteAllByParcheId(Mockito.any())).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        Mockito.when(parcheRepository.deleteById("yyyy")).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));

        webTestClient.delete()
                .uri("/parches/eliminar/yyyy")
                .exchange()
                .expectStatus().isBadRequest();

        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteAllByParcheId(Mockito.any());
        Mockito.verify(comentarioRepository, Mockito.times(1)).deleteAllByParcheId(Mockito.any());
        Mockito.verify(parcheRepository, Mockito.times(1)).deleteById("yyyy");

    }

}