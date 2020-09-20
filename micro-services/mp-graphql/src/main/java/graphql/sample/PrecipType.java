
package graphql.sample;

public enum PrecipType {
    RAIN,
    SNOW,
    SLEET;

    static PrecipType fromTempF(double tempF) {
        if (tempF > 40) {
            return RAIN;
        }
        if (tempF > 35) {
            return SLEET;
        }
        return SNOW;
    }
}
