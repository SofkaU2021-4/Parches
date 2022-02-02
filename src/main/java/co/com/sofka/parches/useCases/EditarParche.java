package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface EditarParche {
    Mono<ParcheDTO> editarParche(ParcheDTO parcheDTO);
}
