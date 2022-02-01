package co.com.sofka.parches.useCases;

import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class EliminarParcheUseCase implements EliminarParche{
    private final ParcheRepository parcheRepository;
    private final ComentarioRepository comentarioRepository;
    private final InscripcionRepository inscripcionrepository;

    public EliminarParcheUseCase(ParcheRepository parcheRepository,
                                 ComentarioRepository comentarioRepository,
                                 InscripcionRepository inscripcionrepository){
        this.parcheRepository = parcheRepository;
        this.comentarioRepository = comentarioRepository;
        this.inscripcionrepository = inscripcionrepository;
    }

    @Override
    public Mono<Void> eliminarParche(String parcheId) {
        return comentarioRepository.deleteAllByParcheId(parcheId)
                .and(inscripcionrepository.deleteAllByParcheId(parcheId))
                .and(parcheRepository.deleteById(parcheId));
    }
}
