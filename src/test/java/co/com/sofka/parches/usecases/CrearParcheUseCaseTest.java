package co.com.sofka.parches.usecases;

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

    private static final String NOMBRE_PARCHE_CREAR_PARCHE = "parche de prueba";
    private static final String DESCRIPCION_CREAR_PARCHE = "descripcion de prueba";
    private static final String FECHA_CREACION_CREAR_PARCHE = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO_CREAR_PARCHE = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN_CREAR_PARCHE = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO_CREAR_PARCHE = Estado.HABILITADO;
    private static final Categoria CATEGORIA_CREAR_PARCHE = Categoria.APRENDIZAJE;
    private static final Long CAPACIDAD_CREAR_PARCHE = 10L;
    private static final Double LAT_CREAR_PARCHE = 1.0;
    private static final Double LNG_CREAR_PARCHE = 1.0;
    private static final String DIRECCION_CREAR_PARCHE = "aaa";

    @BeforeEach
    public void setup(){
        ParcheMapper parcheMapper = new ParcheMapper();
        parcheRepository = Mockito.mock(ParcheRepository.class);
        crearParcheUseCase = new CrearParcheUseCase(parcheRepository, parcheMapper);
    }

    @Test
    void crearParcheUseCaseTest(){
        var parche = new Parche();
        parche.setId("xxxx");
        parche.setDuenoDelParche("yyyy");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE_CREAR_PARCHE));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION_CREAR_PARCHE));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION_CREAR_PARCHE));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO_CREAR_PARCHE));
        parche.setFechaFin(new FechaParche(FECHA_FIN_CREAR_PARCHE));
        parche.setEstado(ESTADO_CREAR_PARCHE);
        parche.setCategoria(CATEGORIA_CREAR_PARCHE);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_CREAR_PARCHE));
        parche.setUbicacion(new UbicacionParche(LAT_CREAR_PARCHE, LNG_CREAR_PARCHE, DIRECCION_CREAR_PARCHE));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("xxxx");
        parcheDTO.setDuenoDelParche("yyyy");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE_CREAR_PARCHE));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION_CREAR_PARCHE));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION_CREAR_PARCHE));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO_CREAR_PARCHE));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN_CREAR_PARCHE));
        parcheDTO.setEstado(ESTADO_CREAR_PARCHE);
        parcheDTO.setCategoria(CATEGORIA_CREAR_PARCHE);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_CREAR_PARCHE));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT_CREAR_PARCHE, LNG_CREAR_PARCHE, DIRECCION_CREAR_PARCHE));


        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        StepVerifier.create(crearParcheUseCase.crearParche(parcheDTO))
                .expectNextMatches(parchesDTO -> {
                    assert parchesDTO.getId().equals(parcheDTO.getId());
                    assert parchesDTO.getDuenoDelParche().equals(parcheDTO.getDuenoDelParche());
                    assert parchesDTO.getNombreParche().getValorNombre().equals(parcheDTO.getNombreParche().getValorNombre());
                    assert parchesDTO.getDescripcion().getValorDescripcion().equals(parcheDTO.getDescripcion().getValorDescripcion());
                    assert parchesDTO.getFechaInicio().getValorFecha().toString().equals(parcheDTO.getFechaInicio().getValorFecha().toString());
                    assert parchesDTO.getFechaFin().getValorFecha().toString().equals(parcheDTO.getFechaFin().getValorFecha().toString());
                    assert parchesDTO.getEstado().equals(parcheDTO.getEstado());
                    assert parchesDTO.getCategoria().equals(parcheDTO.getCategoria());
                    assert parchesDTO.getCapacidadMaxima().getValorCapacidad().equals(parcheDTO.getCapacidadMaxima().getValorCapacidad());
                    assert parchesDTO.getUbicacionParche().getLat().equals(parcheDTO.getUbicacionParche().getLat());
                    assert parchesDTO.getUbicacionParche().getLng().equals(parcheDTO.getUbicacionParche().getLng());
                    return true;
                })
                .verifyComplete();

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());

    }

}