package com.mastertheboss;

 

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
 
 
 
public class AppTest extends JbpmJUnitBaseTestCase {

	@Test
	public void testProcess() {
		/*
		 * RuntimeManager manager =
		 * createRuntimeManager("com/mastertheboss/sample.bpmn"); RuntimeEngine engine =
		 * getRuntimeEngine(null); KieSession ksession = engine.getKieSession();
		 * TaskService taskService = engine.getTaskService();
		 * 
		 * ProcessInstance processInstance =
		 * ksession.startProcess("com.mastertheboss.bpmn.hello");
		 */

        final RuntimeManager runtimeManager = createRuntimeManager("com/mastertheboss/LicenseDemo.bpmn");
        final RuntimeEngine engine = getRuntimeEngine(null);
        final KieSession ksession = engine.getKieSession();

       

        final ProcessInstance processInstance = ksession.startProcess("com.mastertheboss.LicenseDemo");
        TaskService taskService = engine.getTaskService();
		assertProcessInstanceActive(processInstance.getId(), ksession);
		assertNodeTriggered(processInstance.getId(), "CheckLicense");
		
		// let john execute Task 1
		List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
		TaskSummary task = list.get(0);
		System.out.println("John is executing task " + task.getName());
		
       // taskService.claim(task.getId(), "john");
        taskService.start(task.getId(), "john");
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("age", new Integer(21));

        taskService.complete(task.getId(), "john", results);
  
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
		
		manager.disposeRuntimeEngine(engine);
		manager.close();
	}
	
	public AppTest() {
		super(true, true);
	}

}