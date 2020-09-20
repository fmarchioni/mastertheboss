

package graphql.sample;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
@ApplicationScoped
public class WeatherService {

    Map<String, Conditions> currentConditionsMap = new HashMap<>();


    @Query
    public Conditions currentConditions(@Name("location") String location) throws UnknownLocationException {
        if ("nowhere".equalsIgnoreCase(location)) {
            throw new UnknownLocationException(location);
        }
        return currentConditionsMap.computeIfAbsent(location, this::randomWeatherConditions);
    }

    @Query
    public List<Conditions> currentConditionsList(@Name("locations") List<String> locations)
        throws UnknownLocationException, GraphQLException {

        List<Conditions> allConditions = new LinkedList<>();
        for (String location : locations) {
            try {
                allConditions.add(currentConditions(location));
            } catch (UnknownLocationException ule) {
                throw new GraphQLException(ule, allConditions);
            }
        }
        return allConditions;
    }

    @Mutation
    @Description("Reset the cached conditions so that new queries will return newly randomized weather data." +
                 "Returns number of entries cleared.")
    public int reset() {
        int cleared = currentConditionsMap.size();
        currentConditionsMap.clear();
        return cleared;
    }

    public double wetBulbTempF(@Source @Name("conditions") Conditions conditions) {
        // TODO: pretend like this is a really expensive operation
        System.out.println("wetBulbTempF for location " + conditions.getLocation());
        return conditions.getTemperatureF() - 3.0;
    }

    private Conditions randomWeatherConditions(String location) {
        Conditions c = new Conditions(location);
        c.setDayTime(Math.random() > 0.5);
        c.setTemperatureF(Math.random() * 100);
        c.setTemperatureC( (c.getTemperatureF() - 30) / 2 );
        c.setHasPrecipitation(Math.random() > 0.7);
        c.setPrecipitationType(c.isHasPrecipitation() ? PrecipType.fromTempF(c.getTemperatureF()) : null);
        c.setWeatherText(c.isHasPrecipitation() ? "Overcast" : "Sunny");
        return c;
    }
}
