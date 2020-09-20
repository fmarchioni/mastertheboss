
package graphql.sample.client;


public class TempAndPrecip {

    private double temperatureF;
    private boolean hasPrecipitation;

    public double getTemperatureF() {
        return temperatureF;
    }

    public void setTemperatureF(double temperatureF) {
        this.temperatureF = temperatureF;
    }

    public boolean ishasPrecipitation() {
        return hasPrecipitation;
    }

    public void setHasPrecipitation(boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
    }

    @Override
    public String toString() {
        return "Temperature (F): " + temperatureF + " Precipitation: " + hasPrecipitation;
    }
}