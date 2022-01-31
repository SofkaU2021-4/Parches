package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ParcheRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Validated
public class ListarMisParchesCreadosUseCase implements ListarMisParchesCreados {

    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;

    public ListarMisParchesCreadosUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
    }

    @Override
    public Flux<ParcheDTO> listarMisParchesCreados(String duenoDelParche) {
        Objects.requireNonNull(duenoDelParche, "Id del dueño del parche es requerido");
        return parcheRepository.findAllByDuenoDelParche(duenoDelParche)
                .map(parcheMapper.mapToDTO());
    }
}
