package org.acme;


import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/hello")
public class GreetingResource {
   @Inject
    DataSource ds;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTime() {

        String toReturn=null;
        try (Connection con = ds.getConnection();

             PreparedStatement ps = con.prepareStatement("SELECT CURRENT_TIMESTAMP");) {

            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                toReturn = "Current time: " + rs.getTimestamp(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}