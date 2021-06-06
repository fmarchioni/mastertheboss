package com.company.service;

import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.query.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SpringBootApplication
@RestController
public class Application  {

    @Autowired
    private ProcessService processService;
    @Autowired
    private RuntimeDataService runtimeDataService;
    @Autowired
    private UserTaskService userTaskService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(@RequestParam Integer age) throws Exception {


        // Provided as an example. Not actually needed by our process.
        Map<String, Object> vars = new HashMap<>();
        vars.put("name", "John Smith");

        Long processInstanceId = processService.startProcess("business-application-kjar-1_0-SNAPSHOT", "com.mastertheboss.LicenseDemo", vars);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("age", age);

        List<TaskSummary> taskSummaries = runtimeDataService.getTasksAssignedAsPotentialOwner("john", new QueryFilter());

        taskSummaries.forEach(s->{
            Status status = taskSummaries.get(0).getStatus();
            if ( status == Status.Ready )
                userTaskService.claim(s.getId(), "john");
            userTaskService.start(s.getId(), "john");
            userTaskService.complete(s.getId(), "john", params);
        });



        return ResponseEntity.status(HttpStatus.CREATED).body("Task completed!");
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> datamodelCallback(Principal principal, @RequestBody String name) throws Exception {
        System.out.println("Hey : "+ principal);
        System.out.println("There is a license request from " + name);
        return ResponseEntity.ok().build();
    }
}