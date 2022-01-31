package co.com.sofka.parches.useCases;

import co.com.sofka.parches.personal.PruebaModel;
import co.com.sofka.parches.personal.Repositorio;
import co.com.sofka.parches.utils.Validaciones;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;


@Service
@Validated
public class InicoSesionCasoDeUso implements Function<String, Mono<PruebaModel>> {

    private final Repositorio repositorio;

    public InicoSesionCasoDeUso(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono<PruebaModel> apply(String UID) {
        return new Validaciones(repositorio).verificar(UID)
                //.onErrorResume(error -> Mono.error(new IllegalStateException("asdasd")));
                //.switchIfEmpty(Mono.error(new IllegalStateException("asdasd")));
                ;

    }
}
