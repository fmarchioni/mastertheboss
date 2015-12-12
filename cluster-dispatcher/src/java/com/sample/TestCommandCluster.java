package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author francesco
 */
@WebServlet(name = "TestCommandCluster", urlPatterns = {"/TestCommandCluster"})
public class TestCommandCluster extends HttpServlet {

    @Resource(lookup = "java:jboss/clustering/group/web")
    private Group channelGroup;

    @Resource(lookup = "java:jboss/clustering/dispatcher/web")
    private CommandDispatcherFactory factory;

    private final Command<String, Node> command = new GarbageCollectorCommand();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    CommandDispatcherBean ejb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            List<Node> nodes = channelGroup.getNodes();
            Map<Node, CommandResponse<String>> map = ejb.executeOnCluster(command, null);

            for (Map.Entry<Node, CommandResponse<String>> entry : map.entrySet()) {
                out.println(entry.getValue() + " executed on " + entry.getKey());
            }

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
