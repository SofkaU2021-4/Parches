package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.useCases.ListarMisParchesCreadosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ListarMisParchesCreadosRouter {

    @Bean
    public RouterFunction<ServerResponse> listarMisParchesCreados(ListarMisParchesCreadosUseCase listarMisParchesCreadosUseCase) {
        return route(GET("/{usuarioId}/misParches"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                listarMisParchesCreadosUseCase.listarMisParchesCreados(
                                        request.pathVariable("usuarioId")),
                                ParcheDTO.class))
                        .onErrorResume(throwable -> ServerResponse.badRequest().body(throwable.getMessage(), String.class))
        );
    }

}
