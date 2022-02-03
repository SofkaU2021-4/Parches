package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.mappers.InscripcionMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.valueObjects.FechaParche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


class CrearInscripcionUseCaseTest {

    InscripcionRepository inscripcionRepository;
    InscripcionMapper inscripcionMapper;
    CrearInscripcionUseCase crearInscripcionUseCase;

    @BeforeEach
    void setup() {
        inscripcionRepository = mock(InscripcionRepository.class);
        inscripcionMapper = new InscripcionMapper();
        crearInscripcionUseCase = new CrearInscripcionUseCase(inscripcionRepository,inscripcionMapper);
    }

    @Test
    void crearInscripcion(){
        when(inscripcionRepository.save(any())).thenReturn(Mono.just(inscripcion()));
        StepVerifier.create(crearInscripcionUseCase.crearInscripcion("Parche005","uia002"))
                .expectNextMatches(inscripcionDTO -> {
                    assert inscripcionDTO.getId().equals("inscripcion0001");
                    assert inscripcionDTO.getParcheId().equals("Parche005");
                    assert inscripcionDTO.getUsuarioId().equals("uia002");
                    assert inscripcionDTO.getFechaDeInscripcion().getValorFecha().toString().equals("2022-02-01T21:06:42.140");
                    return true;
                })
                .verifyComplete();
        verify(inscripcionRepository).save(any());
    }

    private Inscripcion inscripcion() {
        return new Inscripcion(
                "inscripcion0001",
                "Parche005",
                "uia002",
                fechaInscripcion());
    }

    private FechaParche fechaInscripcion() {
        return new FechaParche("2022-02-01T21:06:42.14");
    }



}