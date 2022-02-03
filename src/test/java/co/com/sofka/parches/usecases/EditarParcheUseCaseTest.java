package co.com.sofka.parches.usecases;

import co.com.sofka.parches.collections.Parche;
import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.enums.Categoria;
import co.com.sofka.parches.enums.Estado;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import co.com.sofka.parches.valueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class EditarParcheUseCaseTest {

    ParcheRepository parcheRepository;
    InscripcionRepository inscripcionRepository;
    EditarParcheUseCase editarParcheUseCase;

    private static final String NOMBRE_PARCHE = "parche de prueba";
    private static final String DESCRIPCION = "descripcion de prueba";
    private static final String FECHA_CREACION = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO = Estado.HABILITADO;
    private static final Categoria CATEGORIA = Categoria.APRENDIZAJE;
    private static final Long CAPACIDAD = 10L;
    private static final Double LAT = 1.0;
    private static final Double LNG = 1.0;
    private static final String DIRECCION = "aaa";

    @BeforeEach
    public void setup(){
        ParcheMapper parcheMapper = new ParcheMapper();
        parcheRepository = Mockito.mock(ParcheRepository.class);
        inscripcionRepository = Mockito.mock(InscripcionRepository.class);
        editarParcheUseCase = new EditarParcheUseCase(parcheRepository, parcheMapper, inscripcionRepository);
    }

    @Test
    void editarParcheUseCaseTest(){
        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO));
        parche.setFechaFin(new FechaParche(FECHA_FIN));
        parche.setEstado(ESTADO);
        parche.setCategoria(CATEGORIA);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD));
        parche.setUbicacion(new UbicacionParche(LAT, LNG, DIRECCION));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN));
        parcheDTO.setEstado(ESTADO);
        parcheDTO.setCategoria(CATEGORIA);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT, LNG, DIRECCION));


        Mockito.when(parcheRepository.findById(parche.getId())).thenReturn(Mono.just(parche));
        Mockito.when(inscripcionRepository.countAllByParcheId(parche.getId())).thenReturn(Mono.just(2L));
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        StepVerifier.create(editarParcheUseCase.editarParche(parcheDTO))
                .expectNextMatches(parcheDTO1 -> {
                    assert parcheDTO1.getId().equals(parcheDTO.getId());
                    assert parcheDTO1.getDuenoDelParche().equals(parcheDTO.getDuenoDelParche());
                    assert parcheDTO1.getNombreParche().getValorNombre().equals(parcheDTO.getNombreParche().getValorNombre());
                    assert parcheDTO1.getDescripcion().getValorDescripcion().equals(parcheDTO.getDescripcion().getValorDescripcion());
                    assert parcheDTO1.getFechaInicio().getValorFecha().toString().equals(parcheDTO.getFechaInicio().getValorFecha().toString());
                    assert parcheDTO1.getFechaFin().getValorFecha().toString().equals(parcheDTO.getFechaFin().getValorFecha().toString());
                    assert parcheDTO1.getEstado().equals(parcheDTO.getEstado());
                    assert parcheDTO1.getCategoria().equals(parcheDTO.getCategoria());
                    assert parcheDTO1.getCapacidadMaxima().getValorCapacidad().equals(parcheDTO.getCapacidadMaxima().getValorCapacidad());
                    assert parcheDTO1.getUbicacionParche().getLat().equals(parcheDTO.getUbicacionParche().getLat());
                    assert parcheDTO1.getUbicacionParche().getLng().equals(parcheDTO.getUbicacionParche().getLng());
                    return true;
                })
                .verifyComplete();

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(inscripcionRepository, Mockito.times(1)).countAllByParcheId(parche.getId());
        Mockito.verify(parcheRepository, Mockito.times(1)).findById(parche.getId());

    }

    @Test
    void editarParcheUseCaseErrorCapacidad(){
        var parcheCaseError = new Parche();
        parcheCaseError.setId("xxxx");
        parcheCaseError.setDuenoDelParche("yyyy");
        parcheCaseError.setNombreParche(new NombreParche(NOMBRE_PARCHE));
        parcheCaseError.setDescripcion(new DescripcionParche(DESCRIPCION));
        parcheCaseError.setFechaCreacion(new FechaParche(FECHA_CREACION));
        parcheCaseError.setFechaInicio(new FechaParche(FECHA_INICIO));
        parcheCaseError.setFechaFin(new FechaParche(FECHA_FIN));
        parcheCaseError.setEstado(ESTADO);
        parcheCaseError.setCategoria(CATEGORIA);
        parcheCaseError.setCapacidadMaxima(new CapacidadParche(CAPACIDAD));
        parcheCaseError.setUbicacion(new UbicacionParche(LAT, LNG, DIRECCION));

        var parcheDTOcaseError = new ParcheDTO();
        parcheDTOcaseError.setId("xxxx");
        parcheDTOcaseError.setDuenoDelParche("yyyy");
        parcheDTOcaseError.setNombreParche(new NombreParche(NOMBRE_PARCHE));
        parcheDTOcaseError.setDescripcion(new DescripcionParche(DESCRIPCION));
        parcheDTOcaseError.setFechaCreacion(new FechaParche(FECHA_CREACION));
        parcheDTOcaseError.setFechaInicio(new FechaParche(FECHA_INICIO));
        parcheDTOcaseError.setFechaFin(new FechaParche(FECHA_FIN));
        parcheDTOcaseError.setEstado(ESTADO);
        parcheDTOcaseError.setCategoria(CATEGORIA);
        parcheDTOcaseError.setCapacidadMaxima(new CapacidadParche(CAPACIDAD));
        parcheDTOcaseError.setUbicacionParche(new UbicacionParche(LAT, LNG, DIRECCION));


        Mockito.when(parcheRepository.findById(parcheCaseError.getId())).thenReturn(Mono.just(parcheCaseError));
        Mockito.when(inscripcionRepository.countAllByParcheId(parcheCaseError.getId())).thenReturn(Mono.just(20L));
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parcheCaseError));

        StepVerifier.create(editarParcheUseCase.editarParche(parcheDTOcaseError))
                .expectErrorMessage("Error: la capacidad maxima no puede ser menor que el numero de usuarios inscritos")
                .verify();

    }

}