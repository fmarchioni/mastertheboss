package com.mastertheboss.util;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.enterprise.inject.spi.BeanManager;
import org.kie.api.task.TaskService;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.cdi.Kjar;
import org.jbpm.services.task.audit.JPATaskLifeCycleEventListener;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.jbpm.services.task.lifecycle.listeners.TaskLifeCycleEventListener;
import org.kie.internal.task.api.UserGroupCallback;
@ApplicationScoped
public class EnvironmentProducer {
	 @Inject
	    private BeanManager beanManager;
	    private TaskService taskService;
	    private EntityManagerFactory emf;
	   @Produces    
	    public UserGroupCallback produceSelectedUserGroupCalback() {
	        return new JBossUserGroupCallbackImpl("classpath:/usergroup.properties");
	    }
	    
	    @PersistenceUnit(unitName = "org.jbpm.sample")
	    @ApplicationScoped
	    @Produces
	    public EntityManagerFactory getEntityManagerFactory() {
	        if (this.emf == null) {
	            this.emf = Persistence.createEntityManagerFactory("org.jbpm.sample");
	        }
	        return this.emf;
	    } 
 

}