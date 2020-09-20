
package graphql.sample.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.smallrye.graphql.client.typesafe.api.GraphQlClientBuilder;


@WebServlet("/demo")
public class GraphQLServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeatherApi api = GraphQlClientBuilder.newBuilder().endpoint("http://localhost:8080/"+ request.getContextPath()+"/graphql").build(WeatherApi.class);
        String destination = request.getParameter("destination") != null ? request.getParameter("destination") : "Paris";
        TempAndPrecip tempAndPrecip = api.currentConditions(destination);
        response.getWriter().append(tempAndPrecip.toString());

    }


}