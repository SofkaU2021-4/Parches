package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface ListarMisParchesCreados {
    Flux<ParcheDTO> listarMisParchesCreados(String duenoDelParche);
}
