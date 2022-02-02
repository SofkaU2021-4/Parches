package co.com.sofka.parches.usecases;


import co.com.sofka.parches.dtos.DetallesParcheDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VerDetalleParche {

    Mono<DetallesParcheDTO> verDetalleParche(String idParche, String userId);
}
