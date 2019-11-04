
package com.mastertheboss.kafka;

import fish.payara.cloud.connectors.kafka.api.KafkaConnection;
import fish.payara.cloud.connectors.kafka.api.KafkaConnectionFactory;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/kafka")

@ApplicationScoped
public class KafkaQueueResource {
        
    @Resource(lookup="java:/KafkaConnectionFactory")
    KafkaConnectionFactory factory;

    public KafkaQueueResource() {
    }

    @GET
    public Response hello() {

        try (KafkaConnection conn = factory.createConnection()) {
            conn.send(new ProducerRecord("my-topic","Hello world!"));
            return Response
                    .status(Response.Status.OK)
                    .entity("Message sent!")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return  Response.serverError().entity(ex.getMessage()).build();
        }    

    }
}
