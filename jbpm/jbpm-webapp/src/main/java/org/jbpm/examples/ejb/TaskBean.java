/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.examples.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.jbpm.services.task.exception.PermissionDeniedException;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;

@Stateless
public class TaskBean  {

    @Inject
    TaskService taskService;
    
    public List<TaskSummary> retrieveTaskList(String actorId) throws Exception {

        List<TaskSummary> list;
        
        try {
            list = taskService.getTasksAssignedAsPotentialOwner(actorId, "en-UK");
  
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("retrieveTaskList by " + actorId);
        for (TaskSummary task : list) {
            System.out.println(" task.getId() = " + task.getId());
        }

        return list;
    }

    public void approveTask(String actorId, long taskId) throws Exception {

        try {
            System.out.println("approveTask (taskId = " + taskId + ") by " + actorId);
            taskService.start(taskId, actorId);
        
            taskService.complete(taskId, actorId, null);
 
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            
            throw new RuntimeException(e);
        }  
    }

}
