package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Inscripcion;
import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ListarParchesUseCaseTest {
    ParcheRepository parcheRepository;
    InscripcionRepository inscripcionRepository;
    ListarParchesUseCase listarParchesUseCase;

    @BeforeEach
    public void setup(){
        ParcheMapper parcheMapper = new ParcheMapper();
        parcheRepository = mock(ParcheRepository.class);
        inscripcionRepository = mock(InscripcionRepository.class);

        listarParchesUseCase = new ListarParchesUseCase(parcheRepository, parcheMapper, inscripcionRepository);
    }

    @Test
    void getValidationTest(){

        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("Dueño del Parche");
        parche.setNombreParche(new NombreParche("Nombre Parche"));
        parche.setDescripcion(new DescripcionParche("Descripcion parche"));
        parche.setFechaCreacion(new FechaParche("2022-01-31T17:07:35.361"));
        parche.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche.setEstado(Estado.HABILITADO);
        parche.setCategoria(Categoria.APRENDIZAJE);
        parche.setCapacidadMaxima(new CapacidadParche(50L));
        parche.setUbicacion(new UbicacionParche(1D,1D, ""));

        var inscripcion1 = new Inscripcion();
        inscripcion1.setId("inscritoxxxx");
        inscripcion1.setParcheId("xxxx");

        when(parcheRepository.findAll()).thenReturn(Flux.just(parche));
        when(inscripcionRepository.findAllByParcheId("xxxx")).thenReturn(Flux.just(inscripcion1));

        StepVerifier.create(listarParchesUseCase.apply())
                .expectNextMatches(parcheDTO -> {
                    assert parcheDTO.getId().equals("xxxx");
                    assert parcheDTO.getDuenoDelParche().equals("Dueño del Parche");
                    assert parcheDTO.getNombreParche().getValorNombre().equals("Nombre Parche");
                    assert parcheDTO.getDescripcion().getValorDescripcion().equals("Descripcion parche");
                    assert parcheDTO.getFechaCreacion().getValorFecha().equals(LocalDateTime.parse("2022-01-31T17:07:35.361"));
                    assert parcheDTO.getFechaInicio().getValorFecha().equals(LocalDateTime.parse("2022-12-12T10:59:11.332"));
                    assert parcheDTO.getFechaFin().getValorFecha().equals(LocalDateTime.parse("2022-12-12T12:59:11.332"));
                    assert parcheDTO.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO.getCapacidadMaxima().getValorCapacidad().equals(50L);
                    assert parcheDTO.getUbicacionParche().getLat().equals(1D);
                    assert parcheDTO.getUbicacionParche().getLng().equals(1D);
                    assert parcheDTO.getCantidadParticipantes().getValorCantidadParticipantes().equals(1L);
                    return true;
                })
                .verifyComplete();

        verify(inscripcionRepository).findAllByParcheId("xxxx");
        verify(parcheRepository).findAll();
    }
}