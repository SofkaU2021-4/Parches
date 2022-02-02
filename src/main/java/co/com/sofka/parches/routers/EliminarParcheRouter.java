package co.com.sofka.parches.routers;

import co.com.sofka.parches.useCases.EliminarParcheUseCase;
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
public class EliminarParcheRouter {
    @Bean
    public RouterFunction<ServerResponse> eliminarParcheRouterFunction(EliminarParcheUseCase eliminarParcheUseCase){
        return route(DELETE("/parches/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarParcheUseCase
                                .eliminarParche(request.pathVariable("id")), Void.class))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .body(throwable.getMessage(), String.class))
        );
    }
}
