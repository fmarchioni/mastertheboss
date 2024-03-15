package org.acme;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
 
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
 

import java.sql.*;

@Path("/time")
@Produces("application/json")
@Consumes("application/json")

public class PlainDataSourceResource {

    @Inject
    EntityManager entityManager;

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