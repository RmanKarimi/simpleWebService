package net.atlassian.iotreport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;



@Path("/helloworld")
public class HelloWorldRestService {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{name}")
	public String getGreeting(@Context HttpHeaders httpheaders, @PathParam("name") String name) {
		String greeting= "hello "+ name;
		return greeting;
		
	}

}
