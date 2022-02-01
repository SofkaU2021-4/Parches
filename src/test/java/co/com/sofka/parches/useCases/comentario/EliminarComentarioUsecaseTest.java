package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.repositories.ComentarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class EliminarComentarioUsecaseTest {

    @MockBean
    private ComentarioRepository repository;

    @Autowired
    private EliminarComentarioUsecase eliminarComentarioUsecase;

    @Test
    void eliminarComentarioUseCaseTest(){

        Comentario comentario = new Comentario();
        comentario.setId("xxx");
        comentario.setUserId("yyy");
        comentario.setParcheId("zzz");
        comentario.setComentario("prueba");
        comentario.setFechaCreacion(LocalDateTime.of(2022, 01, 31, 16, 19, 29));

        Mockito.when(repository.findById("xxx")).thenReturn(Mono.just(comentario));
        Mockito.when(repository.deleteById("xxx")).thenReturn(Mono.empty());

        StepVerifier.create(eliminarComentarioUsecase.apply("xxx")).expectNext("Eliminado").verifyComplete();

    }

}