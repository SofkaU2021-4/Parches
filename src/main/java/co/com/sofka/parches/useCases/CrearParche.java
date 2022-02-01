package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CrearParche {
    Mono<ParcheDTO> crearParche(ParcheDTO parcheDTO);
}
