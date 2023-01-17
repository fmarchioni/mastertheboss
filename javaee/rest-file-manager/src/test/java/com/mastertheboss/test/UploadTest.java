package com.mastertheboss.test;
import java.io.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UploadTest {

	String FILENAME="test-file.txt";
	@Test
    public void sendFile() throws Exception {
         
	    Client client = ClientBuilder.newClient();

    	WebTarget target = client.target("http://localhost:8080/rest-file-manager/rest/file/upload");
    	
    	MultipartFormDataOutput mdo = new MultipartFormDataOutput();

    	createFile();

    	File filePath = new File(FILENAME);
    	
    	// Check that file exists

    	assertTrue(filePath.exists());
    	
    	mdo.addFormData("attachment", new FileInputStream(filePath),
    		    MediaType.APPLICATION_OCTET_STREAM_TYPE,filePath.getName());
    	
    	GenericEntity<MultipartFormDataOutput> entity = new GenericEntity<MultipartFormDataOutput>(mdo) {};
    	
    	Response r = target.request().post( Entity.entity(entity, MediaType.MULTIPART_FORM_DATA_TYPE));
    	
    	// Check that upload was successful
    	assertEquals(r.getStatus(), 200);
    	
    	 
}

	private void createFile() {
		try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			writer.println("Some text");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
