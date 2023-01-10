package objects;

public class Area {

    private double latmin;
    private double latmax;
    private double lonmin;
    private double lonmax;

    public Area(double latmin, double latmax, double lonmin, double lonmax) {
        this.latmin = latmin;
        this.latmax = latmax;
        this.lonmin = lonmin;
        this.lonmax = lonmax;
    }

    public double getLatmin() {
        return latmin;
    }

    public double getLatmax() {
        return latmax;
    }

    public double getLonmin() {
        return lonmin;
    }

    public double getLonmax() {
        return lonmax;
    }
}
