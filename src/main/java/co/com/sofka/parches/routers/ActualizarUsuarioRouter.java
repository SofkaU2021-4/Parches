package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.UsuarioDTO;
import co.com.sofka.parches.useCases.ActualizarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarUsuarioRouter {
    @Bean
    public RouterFunction<ServerResponse> actualizarUsuario(ActualizarUsuarioUseCase actualizarUsuarioUseCase) {

        Function<UsuarioDTO, Mono<ServerResponse>> executor = usuarioDTO -> actualizarUsuarioUseCase.apply(usuarioDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/actualizarUsuario").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UsuarioDTO.class)
                        .flatMap(executor)
        );
    }
}