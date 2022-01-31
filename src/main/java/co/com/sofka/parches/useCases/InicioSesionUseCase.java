package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Usuario;
import co.com.sofka.parches.repositories.UsuarioRepository;
import co.com.sofka.parches.utils.Validaciones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Service
@Validated
public class InicioSesionUseCase implements Function<String, Mono<Usuario>> {

    private final UsuarioRepository repositorio;

    public InicioSesionUseCase(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono<Usuario> apply(String UID) {
        return new Validaciones(repositorio).verificarExistenciaUsuarioMongoYFirebaseParaIniciarSesion(UID)
                .onErrorResume(error -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));


    }
}
