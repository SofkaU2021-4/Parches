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

    private static final String ID = "xxxx";
    private static final String UID = "0000";
    private static final String NOMBRES = "Felipe Rodriguez";
    private static final String EMAIL = "felipe@gmail.com";
    private static final String IMAGEN_URL = "imagen";

    UsuarioDTO usuarioDTO = new UsuarioDTO(
            ID,
            UID,
            NOMBRES,
            EMAIL,
            IMAGEN_URL
    );

    @BeforeEach
    void setup() {
        mapperUtils = new MapperUtils();
        validaciones = mock(Validaciones.class);
        usuarioRepository = mock(UsuarioRepository.class);
        crearUsuarioUseCase = new CrearUsuarioUseCase(usuarioRepository, mapperUtils, validaciones);
    }

    @Test
    void crearUsuarioUseCaseTest() {


        Usuario usuario1 = mapperUtils.mapperDTOaEntidadUsuario(null).apply(usuarioDTO);

        Usuario usuario = mapperUtils.mapperDTOaEntidadUsuario("xxxx").apply(usuarioDTO);

        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(validaciones
                .verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(Mockito.any()))
                .thenReturn(Mono.empty());

        StepVerifier.create(crearUsuarioUseCase.apply(usuarioDTO))
                .expectNextMatches(usuarioId -> {
                    assert usuarioId.getId().equals(ID);
                    assert usuarioId.getUid().equals(UID);
                    assert usuarioId.getNombres().equals(NOMBRES);
                    assert usuarioId.getEmail().equals(EMAIL);
                    assert usuarioId.getImagenUrl().equals(IMAGEN_URL);
                    return true;
                }).verifyComplete();

        Mockito.verify(usuarioRepository).save(refEq(usuario1));
        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(usuarioDTO);

    }

    @Test
    void crearUsuarioErrorUseCaseTest(){


        Usuario usuario1 = mapperUtils.mapperDTOaEntidadUsuario(null).apply(usuarioDTO);
        Usuario usuario = mapperUtils.mapperDTOaEntidadUsuario("xxxx").apply(usuarioDTO);

        when(usuarioRepository.save(any())).thenReturn(Mono.just(usuario));
        when(validaciones
                .verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(Mockito.any()))
                .thenReturn(Mono.just(usuario1));

        StepVerifier.create(crearUsuarioUseCase.apply(usuarioDTO))
                .expectError(ResponseStatusException.class)
                .verify();

        Mockito.verify(usuarioRepository).save(refEq(usuario1));
        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaCrearUsuario(usuarioDTO);
    }
}