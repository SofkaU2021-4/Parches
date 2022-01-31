package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.UsuarioRepository;
import co.com.sofka.parches.utils.Validaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrearUsuarioUseCaseTest {

    UsuarioRepository usuarioRepository;
    MapperUtils mapperUtils;
    Validaciones validaciones;
    CrearUsuarioUseCase crearUsuarioUseCase;

    @BeforeEach
    void setup() {
        mapperUtils = new MapperUtils();
        validaciones = mock(Validaciones.class);
        usuarioRepository = mock(UsuarioRepository.class);
        crearUsuarioUseCase = new CrearUsuarioUseCase(usuarioRepository, mapperUtils, validaciones);
    }

    @Test
    void crearUsuarioUseCaseTest() {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "xxxx",
                "0000",
                "Felipe Rodriguez",
                "felipe@gmail.com",
                "imagen"
        );

        Usuario usuario1 = mapperUtils.mapperDTOaEntidadUsuario(null).apply(usuarioDTO);

        Usuario usuario = mapperUtils.mapperDTOaEntidadUsuario("xxxx").apply(usuarioDTO);

        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(validaciones
                .verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(Mockito.any()))
                .thenReturn(Mono.empty());

        StepVerifier.create(crearUsuarioUseCase.apply(usuarioDTO))
                .expectNextMatches(usuarioId -> {
                    assert usuarioId.getId().equals("xxxx");
                    assert usuarioId.getUid().equals("0000");
                    assert usuarioId.getNombres().equals("Felipe Rodriguez");
                    assert usuarioId.getEmail().equals("felipe@gmail.com");
                    assert usuarioId.getImagenUrl().equals("imagen");
                    return true;
                }).verifyComplete();

        Mockito.verify(usuarioRepository).save(refEq(usuario1));
        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(usuarioDTO.getUid());

    }

    @Test
    void crearUsuarioErrorUseCaseTest(){

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "xxxx",
                "0000",
                "Felipe Rodriguez",
                "felipe@gmail.com",
                "imagen"
        );

        Usuario usuario1 = mapperUtils.mapperDTOaEntidadUsuario(null).apply(usuarioDTO);
        Usuario usuario = mapperUtils.mapperDTOaEntidadUsuario("xxxx").apply(usuarioDTO);

        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(validaciones
                .verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(Mockito.any()))
                .thenReturn(Mono.just(usuario1));

        StepVerifier.create(crearUsuarioUseCase.apply(usuarioDTO))
                .expectError(ResponseStatusException.class);

        Mockito.verify(usuarioRepository).save(refEq(usuario1));
        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(usuarioDTO.getUid());
    }
}