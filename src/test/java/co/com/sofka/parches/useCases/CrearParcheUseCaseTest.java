package co.com.sofka.parches.useCases;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CrearParcheUseCaseTest {

    ParcheRepository parcheRepository;
    CrearParcheUseCase crearParcheUseCase;

    @BeforeEach
    public void setup(){
        ParcheMapper parcheMapper = new ParcheMapper();
        parcheRepository = Mockito.mock(ParcheRepository.class);
        crearParcheUseCase = new CrearParcheUseCase(parcheRepository, parcheMapper);
    }

    @Test
    void crearParcheUseCase(){
        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche("parche de prueba"));
        parche.setDescripcion(new DescripcionParche("descripcion de prueba"));
        parche.setFechaCreacion(new FechaParche("2022-12-12T08:59:11.332"));
        parche.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parche.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parche.setEstado(Estado.HABILITADO);
        parche.setCategoria(Categoria.APRENDIZAJE);
        parche.setCapacidadMaxima(new CapacidadParche(10L));
        parche.setUbicacion(new UbicacionParche(1.0, 1.0, "aaa"));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche("parche de prueba"));
        parcheDTO.setDescripcion(new DescripcionParche("descripcion de prueba"));
        parcheDTO.setFechaInicio(new FechaParche("2022-12-12T10:59:11.332"));
        parcheDTO.setFechaFin(new FechaParche("2022-12-12T12:59:11.332"));
        parcheDTO.setEstado(Estado.HABILITADO);
        parcheDTO.setCategoria(Categoria.APRENDIZAJE);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(10L));
        parcheDTO.setUbicacionParche(new UbicacionParche(1.0, 1.0, "aaa"));


        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        StepVerifier.create(crearParcheUseCase.crearParche(parcheDTO))
                .expectNextMatches(parcheDTO1 -> {
                    assert parcheDTO1.getId().equals("xxxx");
                    assert parcheDTO1.getDuenoDelParche().equals("yyyy");
                    assert parcheDTO1.getNombreParche().getValorNombre().equals("parche de prueba");
                    assert parcheDTO1.getDescripcion().getValorDescripcion().equals("descripcion de prueba");
                    assert parcheDTO1.getFechaInicio().getValorFecha().toString().equals("2022-12-12T10:59:11.332");
                    assert parcheDTO1.getFechaFin().getValorFecha().toString().equals("2022-12-12T12:59:11.332");
                    assert parcheDTO1.getEstado().equals(Estado.HABILITADO);
                    assert parcheDTO1.getCategoria().equals(Categoria.APRENDIZAJE);
                    assert parcheDTO1.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO1.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO1.getUbicacionParche().getLng().equals(1.0);
                    return true;
                })
                .verifyComplete();

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());

    }

}