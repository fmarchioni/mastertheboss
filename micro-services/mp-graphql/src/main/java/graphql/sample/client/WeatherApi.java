
package graphql.sample.client;

import org.eclipse.microprofile.graphql.Name;

public interface WeatherApi {

    TempAndPrecip currentConditions(@Name("location")String location);
}