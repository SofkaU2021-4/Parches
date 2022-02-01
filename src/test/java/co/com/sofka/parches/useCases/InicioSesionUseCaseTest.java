package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.utils.Validaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;



class InicioSesionUseCaseTest {

    Validaciones validaciones;
    InicioSesionUseCase useCase;

    private final String idMongo = "idMongo";
    private final String uid = "IdentificacionUsuario";
    private final String nombres = "Dairon Perilla";
    private final String correo = "correo";
    private final String imagen = "imagen";

    @BeforeEach
    public void setUp() {
        MapperUtils mapper = new MapperUtils();
        validaciones = Mockito.mock(Validaciones.class);
        useCase = new InicioSesionUseCase( mapper, validaciones);
    }


    @Test
    void iniciarSesionCorrecto() {
        MapperUtils mapper = new MapperUtils();

        UsuarioDTO respuesta = new UsuarioDTO();
        respuesta.setId(idMongo);
        respuesta.setUid(uid);
        respuesta.setNombres(nombres);
        respuesta.setEmail(correo);
        respuesta.setImagenUrl(imagen);

        Usuario usuario = mapper.mapperDTOaEntidadUsuario(idMongo).apply(respuesta);

        Mockito.when(validaciones
                        .verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(Mockito.any()))
                        .thenReturn(Mono.just(usuario));

        StepVerifier.create(useCase.apply(uid))
                .expectNextMatches(usuarioDTO -> {
                    assert usuarioDTO.getId().equals(idMongo);
                    assert usuarioDTO.getUid().equals(uid);
                    assert usuarioDTO.getNombres().equals(nombres);
                    assert usuarioDTO.getEmail().equals(correo);
                    assert usuarioDTO.getImagenUrl().equals(imagen);
                    return true;
                })
                .verifyComplete();

            Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(uid);

    }


    @Test
    void iniciarSesionError() {

        Mockito.when(validaciones
                        .verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(Mockito.any()))
                .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

        StepVerifier.create(useCase.apply(uid))
                .expectError(ResponseStatusException.class);

        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(uid);

    }


}