package com.mastertheboss.activiti.DemoActiviti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
 
public class App 
{
    public static void main( String[] args )
    {
    	 final ProcessEngine processEngine = ProcessEngineConfiguration
                 .createStandaloneInMemProcessEngineConfiguration()
                 .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                 .setJobExecutorActivate(true)
                 .setDatabaseSchemaUpdate("drop-create")
                 .buildProcessEngine();

         final RepositoryService repositoryService = processEngine
                 .getRepositoryService();
         repositoryService.createDeployment()
                 .addClasspathResource("DemoProcess.bpmn").deploy();
         final Map<String, Object> variables = new HashMap<String, Object>();

 
         final RuntimeService runtimeService = processEngine.getRuntimeService();
       
         ProcessInstance id = runtimeService.startProcessInstanceByKey("myProcess", variables);
         System.out.println("Started Process Id: "+id.getId());
         
         try {
             final TaskService taskService = processEngine.getTaskService();
             final List<Task> tasksSenior = taskService.createTaskQuery().taskAssignee("kermit").list();
             final List<Task> tasksJunior = taskService.createTaskQuery().taskAssignee("gonzo").list();
           
     
             if (tasksSenior.size() > 0) {
                  System.out.println("Found task for Senior " +tasksSenior.get(0).getName() );
                 taskService.claim(tasksSenior.get(0).getId(), "kermit");
 
                 System.out.println("Task " +tasksSenior.get(0).getName() +" terminated");
             }
             else if (tasksJunior.size() > 0) {
                 System.out.println("Found task for Junior " +tasksJunior.get(0).getName() );
                 taskService.claim(tasksJunior.get(0).getId(), "gonzo");

                System.out.println("Task " +tasksJunior.get(0).getName() +" terminated");
            }
             
         } catch (Exception e) {
        	 System.out.println(e.getMessage());
         } finally {

         }
 processEngine.close();
    }
}
