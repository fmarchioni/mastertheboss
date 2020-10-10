package com.mastertheboss.activiti.main;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class App
{
    public static void main( String[] args ) throws Exception
    {

        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("DemoProcess.bpmn").deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        System.out.println(
                "Found process definition ["
                        + processDefinition.getName() + "] with id ["
                        + processDefinition.getId() + "]");

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
