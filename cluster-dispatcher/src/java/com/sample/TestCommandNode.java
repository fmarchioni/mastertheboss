/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.wildfly.clustering.dispatcher.Command;
import org.wildfly.clustering.dispatcher.CommandDispatcherFactory;
import org.wildfly.clustering.dispatcher.CommandResponse;
import org.wildfly.clustering.group.Group;
import org.wildfly.clustering.group.Node;

 
@WebServlet(name = "TestCommandNode", urlPatterns = {"/TestCommandNode"})
public class TestCommandNode extends HttpServlet {

    @Resource(lookup = "java:jboss/clustering/group/web")
    private Group channelGroup;

    @Resource(lookup = "java:jboss/clustering/dispatcher/web")
    private CommandDispatcherFactory factory;

    private final Command<String, Node> command = new GarbageCollectorCommand();

    @EJB
    CommandDispatcherBean ejb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            // Execute command on the first Node
            Node node = channelGroup.getNodes().get(0);

            CommandResponse cr = ejb.executeOnNode(command, node);
            out.println(cr + " executed on " + node.getName());

        } catch (Exception ex) {
            Logger.getLogger(TestCommandCluster.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
