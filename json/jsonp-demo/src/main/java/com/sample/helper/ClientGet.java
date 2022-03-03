package com.sample.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
public class ClientGet {
 
	// http://localhost:8080/RESTfulExample/json/product/get
	public static String get(String resturl) {
		String result = null;
	  try {
 
		URL url = new URL(resturl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
 
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		
		String output=null;
		while ((output = br.readLine()) != null) {
			result = output;
		}
 
		conn.disconnect();
		
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
	  System.out.println("Output from Server .... \n"+result);
	  return result;
	}
 
}