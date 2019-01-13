package com.mastertheboss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.junit.Test;

public class TestRest {

	@Test
    public void sendFile() throws Exception {
         
    	ResteasyClient client = new ResteasyClientBuilder().build();
    	ResteasyWebTarget target = client.target("http://localhost:8080/rest-file-manager/rest/file/upload");
    	
    	MultipartFormDataOutput mdo = new MultipartFormDataOutput();
    	
    	// Specify file name to be uploaded in pom.xml
    	String fileNamePath = System.getProperty("fileName");
    	File filePath = new File(fileNamePath);
    	
    	// Check that file exists
    	assertTrue(filePath.exists());
    	
    	mdo.addFormData("attachment", new FileInputStream(new File(fileNamePath)),
    		    MediaType.APPLICATION_OCTET_STREAM_TYPE,filePath.getName());
    	
    	GenericEntity<MultipartFormDataOutput> entity = new GenericEntity<MultipartFormDataOutput>(mdo) {};
    	
    	Response r = target.request().post( Entity.entity(entity, MediaType.MULTIPART_FORM_DATA_TYPE));
    	
    	// Check that upload was successful
    	assertEquals(r.getStatus(), 200);
    	
    	 
}

}
