package co.com.sofka.parches.routers.comentario;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.useCases.comentario.CrearComentarioUseCase;
import org.assertj.core.api.Assertions;
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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearComentarioRouter.class, CrearComentarioUseCase.class, ComentarioMapper.class})
class CrearComentarioRouterTest {

    @MockBean
    private ComentarioRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void crearComentarioRouterTest(){

        var comentario = new Comentario();
        comentario.setId("xxx");
        comentario.setUserId("yyy");
        comentario.setParcheId("zzz");
        comentario.setComentario("prueba");
        comentario.setFechaCreacion(LocalDateTime.of(2022, 01, 31, 16, 19, 29));

        var comentarioDTO = new ComentarioDTO(
                comentario.getId(),
                comentario.getUserId(),
                comentario.getParcheId(),
                comentario.getComentario(),
                comentario.getFechaCreacion()
        );

        Mono<Comentario> comentarioMono = Mono.just(comentario);

        when(repository.save(any())).thenReturn(Mono.just(comentario));

        webTestClient.post()
                .uri("/crearComentario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(comentarioDTO), ComentarioDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComentarioDTO.class)
                .value(comentarioDTO1 -> {
                    Assertions.assertThat(comentarioDTO1.getId()).isEqualTo(comentario.getId());                    Assertions.assertThat(comentarioDTO1.getId()).isEqualTo(comentario.getId());
                    Assertions.assertThat(comentarioDTO1.getUserId()).isEqualTo(comentario.getUserId());
                    Assertions.assertThat(comentarioDTO1.getParcheId()).isEqualTo(comentario.getParcheId());
                    Assertions.assertThat(comentarioDTO1.getComentario()).isEqualTo(comentario.getComentario());
                    Assertions.assertThat(comentarioDTO1.getFechaCreacion()).isEqualTo(comentario.getFechaCreacion());
                });

        Mockito.verify(repository,Mockito.times(1)).save(any());
    }

}