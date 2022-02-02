package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.mappers.MapperUtils;
import co.com.sofka.parches.utils.Validaciones;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class InicioSesionUseCase implements Function<String, Mono<UsuarioDTO>> {

    private final MapperUtils mapper;
    private final Validaciones validaciones;

    public InicioSesionUseCase(MapperUtils mapper, Validaciones validaciones) {
        this.mapper = mapper;
        this.validaciones = validaciones;
    }

    @Override
    public Mono<UsuarioDTO> apply(String uid) {
        return validaciones.verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(uid)
                .map(usuario -> mapper.mapperEntidadUsuarioaDTO().apply(usuario))
                .onErrorResume(error -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }
}
