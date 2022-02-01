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

    private static final String ID_MONGO = "idMongo";
    private static final String UID = "IdentificacionUsuario";
    private static final String NOMBRES = "Dairon Perilla";
    private static final String CORREO = "correo";
    private static final String IMAGEN = "imagen";

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
        respuesta.setId(ID_MONGO);
        respuesta.setUid(UID);
        respuesta.setNombres(NOMBRES);
        respuesta.setEmail(CORREO);
        respuesta.setImagenUrl(IMAGEN);

        Usuario usuario = mapper.mapperDTOaEntidadUsuario(ID_MONGO).apply(respuesta);

        Mockito.when(validaciones
                        .verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(Mockito.any()))
                        .thenReturn(Mono.just(usuario));

        StepVerifier.create(useCase.apply(UID))
                .expectNextMatches(usuarioDTO -> {
                    assert usuarioDTO.getId().equals(ID_MONGO);
                    assert usuarioDTO.getUid().equals(UID);
                    assert usuarioDTO.getNombres().equals(NOMBRES);
                    assert usuarioDTO.getEmail().equals(CORREO);
                    assert usuarioDTO.getImagenUrl().equals(IMAGEN);
                    return true;
                })
                .verifyComplete();

            Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(UID);

    }


    @Test
    void iniciarSesionError() {

        Mockito.when(validaciones
                        .verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(Mockito.any()))
                .thenReturn(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

        StepVerifier.create(useCase.apply(UID))
                .expectError(ResponseStatusException.class)
                .verify();

        Mockito.verify(validaciones).verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(UID);

    }


}