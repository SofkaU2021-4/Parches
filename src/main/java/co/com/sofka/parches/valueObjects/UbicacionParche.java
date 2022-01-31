package co.com.sofka.parches.valueObjects;

public class UbicacionParche {

    private Double latitud;
    private Double longitud;

    public UbicacionParche(){}

    public UbicacionParche(Double latitud, Double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

}
