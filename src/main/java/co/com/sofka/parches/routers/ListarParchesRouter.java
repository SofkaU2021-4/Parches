package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.useCases.ListarParchesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListarParchesRouter {
    @Bean
    public RouterFunction<ServerResponse> obtenerParches(ListarParchesUseCase listarParchesUseCase){
        return route(
                GET("/parches").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listarParchesUseCase.apply(), ParcheDTO.class))
        );
    }
}
