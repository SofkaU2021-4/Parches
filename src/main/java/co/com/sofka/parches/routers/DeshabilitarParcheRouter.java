package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.useCases.DeshabilitarParcheUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeshabilitarParcheRouter {

    @Bean
    public RouterFunction<ServerResponse> deshabilitarParcheRouterFunction(DeshabilitarParcheUseCase deshabilitarParcheUseCase){
        return route(PUT("/parches/deshabilitar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ParcheDTO.class)
                        .flatMap(deshabilitarParcheUseCase::deshabilitarParche)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .body(throwable.getMessage(), String.class))
                        )
        );
    }

}
