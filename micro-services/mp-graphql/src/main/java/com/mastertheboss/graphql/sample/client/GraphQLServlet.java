package com.mastertheboss.graphql.sample.client;

import com.mastertheboss.graphql.model.Item;
import io.smallrye.graphql.client.typesafe.api.GraphQlClientBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/demo")
public class GraphQLServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemApi api = GraphQlClientBuilder.newBuilder().endpoint("http://localhost:8080/"+ request.getContextPath()+"/graphql").build(ItemApi.class);

        List<Item> list = api.getAllItems();
        for (Item i:list) {
            response.getWriter().append(i.toString());
        }

    }

}