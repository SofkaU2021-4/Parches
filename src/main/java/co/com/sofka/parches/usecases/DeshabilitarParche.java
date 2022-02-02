package co.com.sofka.parches.usecases;


import co.com.sofka.parches.dtos.ParcheDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeshabilitarParche {
    Mono<ParcheDTO> deshabilitarParche(ParcheDTO parcheDTO);
}
