package com.acme.example;

 

import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationDefinition;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.OperationStepHandler;
import org.jboss.as.controller.SimpleOperationDefinitionBuilder;
import org.jboss.as.controller.descriptions.ResourceDescriptionResolver;
import org.jboss.as.controller.descriptions.StandardResourceDescriptionResolver;
 
import org.jboss.as.server.controller.descriptions.ServerDescriptions;
import org.jboss.dmr.ModelNode;

 
public class SimpleDateHandler implements OperationStepHandler {
 
    public static final SimpleDateHandler INSTANCE = new SimpleDateHandler();
/* 
    public static ResourceDescriptionResolver getResourceDescriptionResolver(final String... keyPrefix) {
     
        return new StandardResourceDescriptionResolver("mysubsystem", "getTime", ServerDescriptions.class.getClassLoader(), true, true);
    }
*/
 

            static final OperationDefinition DEFINITION = new SimpleOperationDefinitionBuilder("getTime",SubsystemExtension.getResourceDescriptionResolver("time"))
           .setRuntimeOnly()
            .build();

    @Override
    public void execute(OperationContext context, ModelNode operation) throws OperationFailedException {

        context.addStep(new OperationStepHandler() {
            @Override
            public void execute(OperationContext context, ModelNode operation) throws OperationFailedException {
                // Modifies state, so communicate that
                context.getServiceRegistry(true);

                
                System.out.println("Current time: "+new java.util.Date().toString());
                context.completeStep(OperationContext.RollbackHandler.NOOP_ROLLBACK_HANDLER);
            }
        }, OperationContext.Stage.RUNTIME);
    }
}
