package co.com.sofka.parches.routers;

import co.com.sofka.parches.personal.PruebaModel;
import co.com.sofka.parches.useCases.InicoSesionCasoDeUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InicioSesion {

    @Bean
    public RouterFunction<ServerResponse> iniciarSesion(InicoSesionCasoDeUso casoDeUso) {
        return route(GET("/inicioSesion/{uid}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                casoDeUso.apply(request.pathVariable("uid")), PruebaModel.class
                        ))
                        .onErrorResume(error->ServerResponse.badRequest().build())
        );
    }

}