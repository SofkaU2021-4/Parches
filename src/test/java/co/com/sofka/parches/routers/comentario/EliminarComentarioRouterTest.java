package co.com.sofka.parches.routers.comentario;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.useCases.comentario.EliminarComentarioUsecase;
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
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarComentarioRouter.class, EliminarComentarioUsecase.class, ComentarioMapper.class})
class EliminarComentarioRouterTest {

    @MockBean
    ComentarioRepository repository;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void eliminarComentarioRouterTest(){
        Comentario comentario = new Comentario();
        comentario.setId("xxx");
        comentario.setUserId("yyy");
        comentario.setParcheId("zzz");
        comentario.setComentario("prueba");
        comentario.setFechaCreacion(LocalDateTime.of(2022, 01, 31, 16, 19, 29));

        when(repository.findById("xxx")).thenReturn(Mono.just(comentario));
        when(repository.deleteById("xxx")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/eliminar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("Eliminado");
                });

        Mockito.verify(repository,Mockito.times(1)).deleteById("xxx");
    }

}