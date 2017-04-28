package com.mastertheboss;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/simple")
@Api(value = "Swagger Demo", description = "Swagger Demo")
public class SimpleRESTService {
	@GET
	@Path("/text")
	 @ApiOperation(value = "Hello World Swagger")
	public String getHello () 
	{
		return "hello world!";
	} 
	@GET
	@Path("/json")
	 @ApiOperation(value = "Returns param", notes = "Returns param", response = SimpleProperty.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of param value", response = SimpleProperty.class)})
	@Produces(MediaType.APPLICATION_JSON)
	public SimpleProperty getPropertyJSON () 
	{
        SimpleProperty p = new SimpleProperty("key","value");
		return p;
	}
	@GET
	@Path("/xml")
 
	 @ApiOperation(value = "Returns param", notes = "Returns param", response = SimpleProperty.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of param value", response = SimpleProperty.class)})
	@Produces(MediaType.APPLICATION_XML)
	public SimpleProperty getPropertyXML () 
	{
        SimpleProperty p = new SimpleProperty("key","value");
		return p;
	}
}