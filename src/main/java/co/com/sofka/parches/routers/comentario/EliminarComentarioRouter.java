package co.com.sofka.parches.routers.comentario;


import co.com.sofka.parches.useCases.comentario.EliminarComentarioUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EliminarComentarioRouter {

    @Bean
    public RouterFunction<ServerResponse> eliminarComentario(EliminarComentarioUsecase eliminarComentarioUsecase) {
        return route(DELETE("/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters
                                .fromPublisher(eliminarComentarioUsecase
                                        .apply(request.pathVariable("id")), String.class))
                        .onErrorResume(throwable -> ServerResponse.badRequest().body(throwable.getMessage(),String.class))
        );
    }
}
