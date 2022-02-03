package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.valueObjects.FechaParche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelarInscripcionUseCaseTest {

    InscripcionRepository inscripcionRepository;
    CancelarInscripcionUseCase cancelarInscripcionUseCase;

    @BeforeEach
    void setup() {
        inscripcionRepository = mock(InscripcionRepository.class);
        cancelarInscripcionUseCase =  new CancelarInscripcionUseCase(inscripcionRepository);
    }

    @Test
    void cancelarInscripcion(){
        when(inscripcionRepository.findById((String) any())).thenReturn(Mono.just(inscripcion()));
        when(inscripcionRepository.deleteById((String) any())).thenReturn(Mono.empty());
        StepVerifier.create(cancelarInscripcionUseCase.cancelarInscripcion("xxxxxx"))
                .expectComplete()
                .verify();
        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteById((String) any());
        Mockito.verify(inscripcionRepository, Mockito.times(1)).findById((String) any());
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