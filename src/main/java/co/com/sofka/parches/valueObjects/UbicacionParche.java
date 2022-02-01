package co.com.sofka.parches.valueObjects;

public class UbicacionParche {

    private Double lat;
    private Double lng;
    private String formatted;

    public UbicacionParche(){}

    public UbicacionParche(Double lat, Double lng, String formatted){
        this.lat = lat;
        this.lng = lng;
        this.formatted = formatted;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getFormatted() {
        return formatted;
    }
}
