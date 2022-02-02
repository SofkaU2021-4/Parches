package co.com.sofka.parches.useCases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface EliminarParche {
    Mono<Void> eliminarParche(String parcheId);
}
