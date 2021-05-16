package org.jbpm.examples.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.examples.ejb.ProcessBean;
import org.jbpm.examples.ejb.TaskBean;
import org.kie.api.task.model.TaskSummary;

@WebServlet(name = "task", urlPatterns = { "/task" })
public class TaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessBean processService;
	@EJB
	private TaskBean taskService;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		long processInstanceId = -1;
		try {
			String action = req.getParameter("action");

			if (action.equals("create")) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("money", 2000);

				processInstanceId = processService.startProcess(
						"com.sample.bpmn", params);
				out.println("Process started with Id " + processInstanceId);
				out.println("<br/><a href=\"task?action=submit\">Complete Task</a> <br/>");
			} else if (action.equals("submit")) {
				List<TaskSummary> taskListJohn = taskService
						.retrieveTaskList("john");
				approveAllTasks(taskListJohn, "john");

				out.println("John Appoved ");
				out.println("<a href=\"index.jsp\">Home</a> <br/>");

			}

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	private void approveAllTasks(List<TaskSummary> taskList, String actor) {

		try {

			System.out.println("======== TASK LIST ========");
			for (TaskSummary task : taskList) {

				System.out.println(task.getName());
				System.out.println(task.getId());
				System.out.println(task.getProcessInstanceId());

				taskService.approveTask(actor, task.getId());

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
