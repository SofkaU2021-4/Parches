package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.useCases.EditarParcheUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EditarParcheRouter {

    @Bean
    public RouterFunction<ServerResponse> editarParcheRouterFunction(EditarParcheUseCase editarParcheUseCase){
        return route(PUT("/parches/editar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ParcheDTO.class)
                        .flatMap(editarParcheUseCase::editarParche)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .body(throwable.getMessage(), String.class))
                        )
        );
    }
}
