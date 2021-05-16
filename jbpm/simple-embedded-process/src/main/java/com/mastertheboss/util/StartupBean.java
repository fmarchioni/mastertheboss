package com.mastertheboss.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.ejb.api.DeploymentServiceEJBLocal;


@Singleton
@Startup
public class StartupBean {
	
    public static final String DEPLOYMENT_ID = "com.mastertheboss:jbpmdemo:1.0.0-SNAPSHOT";


    @EJB
    DeploymentServiceEJBLocal deploymentService;

    //@PostConstruct
    public void init() {
    	
        String[] gav = DEPLOYMENT_ID.split(":");

        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
        deploymentService.deploy(deploymentUnit);
    }

}
