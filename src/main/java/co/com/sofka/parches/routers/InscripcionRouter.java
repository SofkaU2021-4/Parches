package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.InscripcionDTO;
import co.com.sofka.parches.useCases.CrearInscripcionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InscripcionRouter {

    @Bean
    public RouterFunction<ServerResponse> crearInscripcion(CrearInscripcionUseCase crearInscripcionUseCase) {
        return route(
                POST("/crear-inscripcion/{parcheId}/{usuarioId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                crearInscripcionUseCase.crearInscripcion(request.pathVariable("parcheId"),
                                        request.pathVariable("usuarioId")),
                                InscripcionDTO.class
                        ))
        );
    }



}
