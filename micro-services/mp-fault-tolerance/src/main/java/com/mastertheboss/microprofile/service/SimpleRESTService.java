package com.mastertheboss.microprofile.service;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;
import java.util.UUID;

@Path("/")
public class SimpleRESTService {

	String cachedData;

	@PostConstruct
	public void init() {
		System.out.println("Called");
		cachedData = UUID.randomUUID().toString();
	}
	@GET
	@Path("/hello")
	@Timeout(250)
	@Fallback(fallbackMethod = "getCachedData")
	public String getData()
	{
		randomSleep();

		return "Random string " + UUID.randomUUID().toString();
	}
	private void randomSleep() {
		try {
			Thread.sleep(new Random().nextInt(400));
		} catch (Exception e) {
            System.out.println("The application is taking too long...");

		}
	}

	public String getCachedData()
	{

		return "Random (cached) String is "+cachedData;
	}
}
