package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.repositories.ComentarioRepository;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


class EliminarParcheUseCaseTest {

    ParcheRepository parcheRepository;
    InscripcionRepository inscripcionRepository;
    EliminarParcheUseCase eliminarParcheUseCase;
    ComentarioRepository comentarioRepository;

    @BeforeEach
    public void setup(){
        parcheRepository = Mockito.mock(ParcheRepository.class);
        inscripcionRepository = Mockito.mock(InscripcionRepository.class);
        comentarioRepository = Mockito.mock(ComentarioRepository.class);
        eliminarParcheUseCase = new EliminarParcheUseCase(parcheRepository, comentarioRepository , inscripcionRepository);
    }

    @Test
    void eliminarParcheTest() {
        var parche = new Parche();
        parche.setId("xxxx");

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");


        Mockito.when(inscripcionRepository.deleteAllByParcheId(parche.getId())).thenReturn(Mono.empty());
        Mockito.when(comentarioRepository.deleteAllByParcheId(parche.getId())).thenReturn(Mono.empty());
        Mockito.when(parcheRepository.deleteById(parche.getId())).thenReturn(Mono.empty());


        StepVerifier.create(eliminarParcheUseCase.eliminarParche(parcheDTO.getId()))
                .verifyComplete();

        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteAllByParcheId(parche.getId());
        Mockito.verify(comentarioRepository, Mockito.times(1)).deleteAllByParcheId(parche.getId());
        Mockito.verify(parcheRepository, Mockito.times(1)).deleteById(parche.getId());

    }

}