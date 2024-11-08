package com.github.elliot.flowabledemo.test;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假流程
 */
public class HolidayProcessDemo {

    public static void main(String[] args) {
        // Create ProcessEngineConfiguration
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // Create ProcessEngine
        ProcessEngine processEngine = cfg.buildProcessEngine();
        // deploy ProcessDefinition
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        // get ProcessDefinition
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("ProcessDefinition:" + processDefinition.getName());
        // start ProcessInstance
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", "Bruce");
        variables.put("nrOfHolidays", 5);
        variables.put("description", "I will go to Japan");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        // query candidateGroup task lists
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup("managers")
                .list();
        System.out.println("You have " + tasks.size() + " tasks.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "-" + tasks.get(i));
        }
        // get task
        Task task = tasks.get(0);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
        // complete
        Map<String, Object> completeVariable = new HashMap<>();
        completeVariable.put("approved", true);
        taskService.complete(task.getId(), completeVariable);
        // get historical Activity Instances
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            System.out.println(historicActivityInstance.getActivityId() + " took " + historicActivityInstance.getDurationInMillis() + "milliseconds");
        }
    }
}
