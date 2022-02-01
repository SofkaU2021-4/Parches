package co.com.sofka.parches.valueObjects;

public class UbicacionParche {

    private Double x;
    private Double y;

    public UbicacionParche(){}

    public UbicacionParche(Double x, Double y){
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

}
