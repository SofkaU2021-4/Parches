package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.repositories.UsuarioRepository;
import co.com.sofka.parches.utils.Validaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


class InicioSesionUseCaseTest {

    UsuarioRepository repository;
    Validaciones validaciones;
    InicioSesionUseCase useCase;


    @BeforeEach
    public void setUp() {
        MapperUtils mapper = new MapperUtils();
        validaciones = Mockito.mock(Validaciones.class);
        repository = Mockito.mock(UsuarioRepository.class);
        useCase = new InicioSesionUseCase(repository, mapper, validaciones);
    }


    @Test
    void iniciarSesionCorrecto() {
        MapperUtils mapper = new MapperUtils();

        String uid = "IdentificacionUsuario";

        UsuarioDTO respuesta = new UsuarioDTO();
        respuesta.setId("idMongo");
        respuesta.setUid(uid);
        respuesta.setNombres("Dairon Perilla");
        respuesta.setEmail("correo");
        respuesta.setImagenUrl("imagen");

        Usuario usuario = mapper.mapperDTOaEntidadUsuario("idMongo").apply(respuesta);

        Mockito.when(repository.findByUid(Mockito.any())).thenReturn(Mono.just(usuario));
        Mockito.when(validaciones
                        .verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(Mockito.any()))
                        .thenReturn(Mono.just(usuario));

        StepVerifier.create(useCase.apply(uid))
                .expectNextMatches(usuarioDTO -> {
                    assert usuarioDTO.getId().equals("idMongo");
                    assert usuarioDTO.getUid().equals(uid);
                    assert usuarioDTO.getNombres().equals("Dairon Perilla");
                    assert usuarioDTO.getEmail().equals("correo");
                    assert usuarioDTO.getImagenUrl().equals("imagen");
                    return true;
                })
                .verifyComplete();


    }

}