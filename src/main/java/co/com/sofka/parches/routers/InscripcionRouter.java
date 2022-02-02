package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.InscripcionDTO;
import co.com.sofka.parches.useCases.CancelarInscripcionUseCase;
import co.com.sofka.parches.useCases.CrearInscripcionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

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

    @Bean
    public RouterFunction<ServerResponse> cancelarInscripcion(CancelarInscripcionUseCase cancelarInscripcionUseCase) {
        return route(
                DELETE("/cancelar-inscripcion/{inscripcionId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(cancelarInscripcionUseCase.cancelarInscripcion(request.pathVariable("inscripcionId")),
                                Void.class))
        );
    }
}

