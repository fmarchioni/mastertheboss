package com.mastertheboss.jaxrs.service;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

@Path("/redis")
public class SimpleRESTService {

	@Inject
	RedisClient redisClient;

	private static final String HASH_KEY = "user-session:123";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEntries() {
		try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
			RedisAsyncCommands<String, String> asyncCommands = connection.async();

			// Get all key-value pairs in the hash
			Map<String, String> result = asyncCommands.hgetall(HASH_KEY).get();

			// Convert the result to a JSON string using JSON-B
			try (Jsonb jsonb = JsonbBuilder.create()) {
				String jsonResult = jsonb.toJson(result);
				return Response.ok(jsonResult).build();
			}

		} catch (ExecutionException | InterruptedException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error retrieving entries: " + e.getMessage())
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to convert to JSON: " + e.getMessage())
					.build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntry(Map<String, String> entry) {
		try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
			RedisAsyncCommands<String, String> asyncCommands = connection.async();

			// Add the new key-value pair to the hash
			asyncCommands.hset(HASH_KEY, entry).get();

			// Respond with the updated hash
			Map<String, String> updatedResult = asyncCommands.hgetall(HASH_KEY).get();
			try (Jsonb jsonb = JsonbBuilder.create()) {
				String jsonResult = jsonb.toJson(updatedResult);
				return Response.ok(jsonResult).build();
			}

		} catch (ExecutionException | InterruptedException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error adding entry: " + e.getMessage())
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to convert to JSON: " + e.getMessage())
					.build();
		}
	}
}
