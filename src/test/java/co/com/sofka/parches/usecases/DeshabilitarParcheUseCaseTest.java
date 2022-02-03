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


class DeshabilitarParcheUseCaseTest {

    ParcheRepository parcheRepository;
    InscripcionRepository inscripcionRepository;
    DeshabilitarParcheUseCase deshabilitarParcheUseCase;

    private static final String NOMBRE_PARCHE_DESHABILITAR_PARCHE = "parche de prueba";
    private static final String DESCRIPCION_DESHABILITAR_PARCHE = "descripcion de prueba";
    private static final String FECHA_CREACION_DESHABILITAR_PARCHE = "2022-12-12T08:59:11.332";
    private static final String FECHA_INICIO_DESHABILITAR_PARCHE = "2022-12-12T10:59:11.332";
    private static final String FECHA_FIN_DESHABILITAR_PARCHE = "2022-12-12T12:59:11.332";
    private static final Estado ESTADO_DESHABILITAR_PARCHE = Estado.DESHABILITADO;
    private static final Estado ESTADO_HABILITADO_DESHABILITAR_PARCHE = Estado.HABILITADO;
    private static final Categoria CATEGORIA_DESHABILITAR_PARCHE = Categoria.CONFERENCIAS;
    private static final Long CAPACIDAD_DESHABILITAR_PARCHE = 10L;
    private static final Double LAT_DESHABILITAR_PARCHE = 1.0;
    private static final Double LNG_DESHABILITAR_PARCHE = 1.0;
    private static final String DIRECCION_DESHABILITAR_PARCHE = "aaa";

    @BeforeEach
    public void setup(){
        ParcheMapper parcheMapper = new ParcheMapper();
        parcheRepository = Mockito.mock(ParcheRepository.class);
        inscripcionRepository = Mockito.mock(InscripcionRepository.class);
        deshabilitarParcheUseCase = new DeshabilitarParcheUseCase(parcheRepository, parcheMapper, inscripcionRepository);
    }

    @Test
    void deshabilitarParcheTest() {
        var parche = new Parche();
        parche.setId("1234");
        parche.setDuenoDelParche("SOY EL DUEÑO");
        parche.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE));
        parche.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE));
        parche.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE));
        parche.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE));
        parche.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE));
        parche.setEstado(ESTADO_DESHABILITAR_PARCHE);
        parche.setCategoria(CATEGORIA_DESHABILITAR_PARCHE);
        parche.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE));
        parche.setUbicacion(new UbicacionParche(LAT_DESHABILITAR_PARCHE, LNG_DESHABILITAR_PARCHE, DIRECCION_DESHABILITAR_PARCHE));

        var parcheDTO = new ParcheDTO();
        parcheDTO.setId("1234");
        parcheDTO.setDuenoDelParche("SOY EL DUEÑO");
        parcheDTO.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE));
        parcheDTO.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE));
        parcheDTO.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE));
        parcheDTO.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE));
        parcheDTO.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE));
        parcheDTO.setEstado(ESTADO_HABILITADO_DESHABILITAR_PARCHE);
        parcheDTO.setCategoria(CATEGORIA_DESHABILITAR_PARCHE);
        parcheDTO.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE));
        parcheDTO.setUbicacionParche(new UbicacionParche(LAT_DESHABILITAR_PARCHE, LNG_DESHABILITAR_PARCHE, DIRECCION_DESHABILITAR_PARCHE));

        Mockito.when(inscripcionRepository.deleteAllByParcheId(Mockito.any())).thenReturn(Mono.empty());
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parche));

        StepVerifier.create(deshabilitarParcheUseCase.deshabilitarParche(parcheDTO))
                .expectNextMatches(parchesDTODes -> {
                    assert parchesDTODes.getId().equals(parche.getId());
                    assert parchesDTODes.getDuenoDelParche().equals(parche.getDuenoDelParche());
                    assert parchesDTODes.getNombreParche().getValorNombre().equals(parche.getNombreParche().getValorNombre());
                    assert parchesDTODes.getDescripcion().getValorDescripcion().equals(parche.getDescripcion().getValorDescripcion());
                    assert parchesDTODes.getFechaInicio().getValorFecha().toString().equals(parche.getFechaInicio().getValorFecha().toString());
                    assert parchesDTODes.getFechaFin().getValorFecha().toString().equals(parche.getFechaFin().getValorFecha().toString());
                    assert parchesDTODes.getEstado().equals(Estado.DESHABILITADO);
                    assert parchesDTODes.getCategoria().equals(Categoria.CONFERENCIAS);
                    assert parchesDTODes.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parchesDTODes.getUbicacionParche().getLat().equals(1.0);
                    assert parchesDTODes.getUbicacionParche().getLng().equals(1.0);
                    return true;
                })
                .verifyComplete();

        Mockito.verify(parcheRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(inscripcionRepository, Mockito.times(1)).deleteAllByParcheId(parche.getId());

    }

    @Test
    void deshabilitarParcheTestDeshabilitado() {
        var parcheDeshabilitarError = new Parche();
        parcheDeshabilitarError.setId("xxxx1");
        parcheDeshabilitarError.setDuenoDelParche("yyyy1");
        parcheDeshabilitarError.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setEstado(ESTADO_DESHABILITAR_PARCHE);
        parcheDeshabilitarError.setCategoria(CATEGORIA_DESHABILITAR_PARCHE);
        parcheDeshabilitarError.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE));
        parcheDeshabilitarError.setUbicacion(new UbicacionParche(LAT_DESHABILITAR_PARCHE, LNG_DESHABILITAR_PARCHE, DIRECCION_DESHABILITAR_PARCHE));

        var parcheDTODeshabilitarError = new ParcheDTO();
        parcheDTODeshabilitarError.setId("xxxx1");
        parcheDTODeshabilitarError.setDuenoDelParche("yyyy1");
        parcheDTODeshabilitarError.setNombreParche(new NombreParche(NOMBRE_PARCHE_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setDescripcion(new DescripcionParche(DESCRIPCION_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setFechaCreacion(new FechaParche(FECHA_CREACION_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setFechaInicio(new FechaParche(FECHA_INICIO_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setFechaFin(new FechaParche(FECHA_FIN_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setEstado(ESTADO_DESHABILITAR_PARCHE);
        parcheDTODeshabilitarError.setCategoria(CATEGORIA_DESHABILITAR_PARCHE);
        parcheDTODeshabilitarError.setCapacidadMaxima(new CapacidadParche(CAPACIDAD_DESHABILITAR_PARCHE));
        parcheDTODeshabilitarError.setUbicacionParche(new UbicacionParche(LAT_DESHABILITAR_PARCHE, LNG_DESHABILITAR_PARCHE, DIRECCION_DESHABILITAR_PARCHE));

        Mockito.when(inscripcionRepository.deleteAllByParcheId(Mockito.any())).thenReturn(Mono.empty());
        Mockito.when(parcheRepository.save(Mockito.any())).thenReturn(Mono.just(parcheDeshabilitarError));

        StepVerifier.create(deshabilitarParcheUseCase.deshabilitarParche(parcheDTODeshabilitarError))
                .expectNextMatches(parcheDTO1 -> {
                    assert parcheDTO1.getId().equals(parcheDeshabilitarError.getId());
                    assert parcheDTO1.getDuenoDelParche().equals(parcheDeshabilitarError.getDuenoDelParche());
                    assert parcheDTO1.getNombreParche().getValorNombre().equals(parcheDeshabilitarError.getNombreParche().getValorNombre());
                    assert parcheDTO1.getDescripcion().getValorDescripcion().equals(parcheDeshabilitarError.getDescripcion().getValorDescripcion());
                    assert parcheDTO1.getFechaInicio().getValorFecha().toString().equals(parcheDeshabilitarError.getFechaInicio().getValorFecha().toString());
                    assert parcheDTO1.getFechaFin().getValorFecha().toString().equals(parcheDeshabilitarError.getFechaFin().getValorFecha().toString());
                    assert parcheDTO1.getEstado().equals(Estado.DESHABILITADO);
                    assert parcheDTO1.getCategoria().equals(Categoria.CONFERENCIAS);
                    assert parcheDTO1.getCapacidadMaxima().getValorCapacidad().equals(10L);
                    assert parcheDTO1.getUbicacionParche().getLat().equals(1.0);
                    assert parcheDTO1.getUbicacionParche().getLng().equals(1.0);
                    return true;
                })
                .verifyComplete();

    }

}