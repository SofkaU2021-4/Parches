package co.com.sofka.parches.routers.comentario;

import co.com.sofka.parches.dtos.ComentarioDTO;
import co.com.sofka.parches.useCases.comentario.CrearComentarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearComentarioRouter {

    @Bean
    public RouterFunction<ServerResponse> crearComentario(CrearComentarioUseCase crearComentarioUseCase) {
        return route(POST("/crearComentario").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ComentarioDTO.class)
                        .flatMap(comentarioDTO -> crearComentarioUseCase.apply(comentarioDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
