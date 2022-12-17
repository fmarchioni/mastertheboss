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

    @GetMapping("/start")
    public ResponseEntity<String> startProcess() throws Exception {


        // Provided as an example. Not actually needed by our process.
        Map<String, Object> vars = new HashMap<>();
        vars.put("processVar1", "Hello");

      //  Long processInstanceId = processService.startProcess("business-application-kjar-1_0-SNAPSHOT", "subprocess_embedded", vars);
       Long processInstanceId = processService.startProcess("business-application-kjar-1_0-SNAPSHOT", "subprocess_reusable", vars);

       return ResponseEntity.status(HttpStatus.CREATED).body("Process started!");
    }
}
