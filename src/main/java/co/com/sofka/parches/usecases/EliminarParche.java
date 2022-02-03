package co.com.sofka.parches.usecases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface EliminarParche {
    Mono<Void> eliminarParche(String parcheId);
}
