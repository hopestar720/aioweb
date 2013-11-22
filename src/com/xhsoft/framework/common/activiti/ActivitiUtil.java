package com.xhsoft.framework.common.activiti;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public class ActivitiUtil {
	
	private static ActivitiUtil instance = new ActivitiUtil();
	
	private ProcessEngine processEngine;
	private ManagementService managementSerivce;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private TaskService taskService;
	private IdentityService identityService;
	private FormService formService;
	private HistoryService historyService ;
	
	private ActivitiUtil(){
		processEngine = ProcessEngines.getDefaultProcessEngine();
		
		managementSerivce = processEngine.getManagementService();
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		identityService = processEngine.getIdentityService();
		formService = processEngine.getFormService();
		historyService = processEngine.getHistoryService();
	}
	
	public static synchronized ActivitiUtil getInstance(){
		return instance;
	}
	
	public void getService(){
		
	}

}
