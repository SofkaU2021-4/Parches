package co.com.sofka.parches.routers;

import co.com.sofka.parches.dtos.DetallesParcheDTO;
import co.com.sofka.parches.useCases.VerDetalleParcheUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class VerDetalleParcheRouter {

    @Bean
    public RouterFunction<ServerResponse> getDetalleParche(VerDetalleParcheUseCase verDetalleParcheUseCaser) {
        return route(
                GET("/detalle-parche/{parcheId}/{usuarioId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                verDetalleParcheUseCaser.verDetalleParche(request.pathVariable("parcheId"),
                                        request.pathVariable("usuarioId")),
                                DetallesParcheDTO.class
                        ))
        );
    }
}
