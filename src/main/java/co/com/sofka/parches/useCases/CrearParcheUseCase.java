package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.utils.ValidadorParche;
import co.com.sofka.parches.valueObjects.FechaParche;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Validated
public class CrearParcheUseCase implements CrearParche{

    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;

    public CrearParcheUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper){
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
    }

    @Override
    public Mono<ParcheDTO> crearParche(ParcheDTO parcheDTO) {
        parcheDTO.setFechaCreacion(new FechaParche(LocalDateTime.now().toString()));
        return  parcheRepository.save(parcheMapper.mapToCollection().apply(ValidadorParche.validarParche(parcheDTO)))
                .map(parcheMapper.mapToDTO());
    }
}
