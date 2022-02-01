package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.repositories.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class ActualizarUsuarioUseCaseTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ActualizarUsuarioUseCase actualizarUsuarioUseCase;

    @Test
    @DisplayName("Actualizar usuario Test: ")
    void actualizarUsuarioUseCasetest() {
        var usuarioDTO = new UsuarioDTO("XXX",
                "YYY",
                "Migelito",
                "migelito@gmail.com",
                "tuimagen.com");

        var usuario = new Usuario("XXX",
                "YYY",
                "Migelito",
                "migelito@gmail.com",
                "tuimagen.com");

        var usuario2 = new Usuario("XXX",
                "YYY",
                "Juanito",
                "migelito@gmail.com",
                "tuimagen.com");

        Mockito.when(usuarioRepository.findById("XXX")).thenReturn(Mono.just(usuario));
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(Mono.just(usuario2));
        StepVerifier.create(actualizarUsuarioUseCase.apply(usuarioDTO))
                .expectNextMatches(usuario1 -> {
                    assert usuario1.getId().equals("XXX");
                    assert usuario1.getUid().equals("YYY");
                    assert usuario1.getNombres().equals("Juanito");
                    assert usuario1.getEmail().equals("migelito@gmail.com");
                    assert usuario1.getImagenUrl().equals("tuimagen.com");
                     return true;
        }).verifyComplete();
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById("XXX");
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any());
    }



}