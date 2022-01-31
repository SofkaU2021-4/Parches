package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.useCases.CrearParcheUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearParcheRouter {

    @Bean
    public RouterFunction<ServerResponse> crearParcheRouterFunction(CrearParcheUseCase crearParcheUseCase){
        return route(POST("/parches/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ParcheDTO.class)
                        .flatMap(crearParcheUseCase::crearParche)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .body(throwable.getMessage(), String.class))
                        )
        );
    }
}
