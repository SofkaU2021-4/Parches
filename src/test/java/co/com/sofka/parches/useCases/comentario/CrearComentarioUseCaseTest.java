package co.com.sofka.parches.useCases.comentario;

import co.com.sofka.parches.collections.Comentario;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class CrearComentarioUseCaseTest {

    @MockBean
    private ComentarioRepository repository;

    @MockBean
    private ParcheRepository parcheRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CrearComentarioUseCase crearComentarioUseCase;

    @Test
    @DisplayName("Crear Comentario Test: ")
    void crearComentarioTest(){
        var comentario = new Comentario();
        comentario.setId("xxx");
        comentario.setUserId("yyy");
        comentario.setParcheId("zzz");
        comentario.setComentario("prueba");
        comentario.setFechaCreacion(LocalDateTime.of(2022, 1, 31, 16, 19, 29));

        var usuario = new Usuario("XXX",
                "YYY",
                "Migelito",
                "migelito@gmail.com",
                "tuimagen.com");

        var parche = new Parche();
        parche.setId("zzz");

        var comentarioDTO = new ComentarioDTO(
                comentario.getId(),
                comentario.getUserId(),
                comentario.getParcheId(),
                comentario.getComentario());
        comentarioDTO.setFechaCreacion( comentario.getFechaCreacion());

        Mockito.when(parcheRepository.findById("zzz")).thenReturn(Mono.just(parche));
        Mockito.when(usuarioRepository.findByUid(anyString())).thenReturn(Mono.just(usuario));
        Mockito.when(repository.save(any())).thenReturn(Mono.just(comentario));

        StepVerifier.create(crearComentarioUseCase.apply(comentarioDTO)).expectNextMatches(comentario1 -> {
            assert comentario1.getId().equals("xxx");
            assert comentario1.getUserId().equals("yyy");
            assert comentario1.getParcheId().equals("zzz");
            assert comentario1.getComentario().equals("prueba");
            assert comentario1.getFechaCreacion().equals(LocalDateTime.of(2022, 1, 31, 16, 19, 29));
            return true;
        }).verifyComplete();

        Mockito.verify(parcheRepository,Mockito.times(1)).findById("zzz");
        Mockito.verify(usuarioRepository,Mockito.times(1)).findByUid(anyString());
        Mockito.verify(repository,Mockito.times(1)).save(any());
    }

}