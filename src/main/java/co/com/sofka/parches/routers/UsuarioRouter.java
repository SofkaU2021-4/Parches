package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.useCases.CrearUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.function.Function;

@Configuration
public class UsuarioRouter {

    @Bean
    public RouterFunction<ServerResponse> createUser(CrearUsuarioUseCase useCase) {
        Function<UsuarioDTO, Mono<ServerResponse>> executor = usuarioDto -> useCase.apply(usuarioDto)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                POST("/crearUsuario").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UsuarioDTO.class).flatMap(executor)
        );
    }
}
