package co.com.sofka.parches.routers;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.ComentarioMapper;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.UsuarioRepository;
import co.com.sofka.parches.routers.comentario.CrearComentarioRouter;
import co.com.sofka.parches.useCases.ActualizarUsuarioUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
@ContextConfiguration(classes = {ActualizarUsuarioRouter.class, ActualizarUsuarioUseCase.class, MapperUtils.class})
class ActualizarUsuarioRouterTest {
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Actualizar usuario Test: ")
    void actualizarUsuarioRouter() {

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
        webTestClient.put()
                .uri("/actualizarUsuario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(usuarioDTO), UsuarioDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UsuarioDTO.class)
                .value(response -> {
                    Assertions.assertEquals(response.getId(), ("XXX"));
                    Assertions.assertEquals(response.getUid(), ("YYY"));
                    Assertions.assertEquals(response.getNombres(), ("Juanito"));
                    Assertions.assertEquals(response.getEmail(), ("migelito@gmail.com"));
                    Assertions.assertEquals(response.getImagenUrl(), ("tuimagen.com"));

                });
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById("XXX");
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any());
    }
}