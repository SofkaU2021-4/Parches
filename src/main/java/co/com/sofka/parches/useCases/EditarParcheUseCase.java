package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.utils.ValidadorParche;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class EditarParcheUseCase implements EditarParche{

    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;
    private final InscripcionRepository inscripcionrepository;

    public EditarParcheUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper,
                               InscripcionRepository inscripcionrepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionrepository = inscripcionrepository;
    }

    @Override
    public Mono<ParcheDTO> editarParche(ParcheDTO parcheDTO) {
        Objects.requireNonNull(parcheDTO.getId());
        return parcheRepository.findById(parcheDTO.getId()).flatMap(parche ->
                inscripcionrepository.countAllByParcheId(parcheDTO.getId())
                .flatMap(value -> {
                    if(parcheDTO.getCapacidadMaxima().getValorCapacidad() > value){
                        return parcheRepository.save(parcheMapper.mapToCollection().apply(ValidadorParche.validarParche(parcheDTO)))
                                .map(parcheMapper.mapToDTO());
                    }
                    return Mono.error(new IllegalArgumentException("Error: la capacidad maxima no puede ser menor que el numero de usuarios inscritos"));
                })).switchIfEmpty(Mono.error(new IllegalArgumentException("Error: el parche no existe")));
    }

}
