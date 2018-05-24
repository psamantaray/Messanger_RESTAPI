package com.first.project.Messanger;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.first.project.Messanger.model.Message;
import com.first.project.Messanger.services.MessageService;

@Path("/textMessages")
public class paramImplementation {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages(@QueryParam("year") int year,
			                            @QueryParam("start") int start, 
			                            @QueryParam("size") int size,
			                            @MatrixParam("year") int year1){
		
		if(year >0) {
			return MessageService.getMessagesForYear(year);
			
		}
		if(start>=0 && size>0) {
			return MessageService.getAllMessagesPaginated(start, size);
		}
		return MessageService.getAllMessages();
	}
	@GET
	@Path("/headerParam")
	@Produces(MediaType.TEXT_PLAIN)
	public String headerParam(@HeaderParam("prasant") String dataKey,
							  @CookieParam("Cookie_2") String cookie) {
		return dataKey+cookie;
	}
	
	@GET
	@Path("/contextParam")
	@Produces(MediaType.APPLICATION_JSON)
	public Map contextParam(@Context UriInfo uriInfo, @Context HttpHeaders header) {
		String path =uriInfo.getAbsolutePath().toString();
		Map h = header.getRequestHeaders();
		MultivaluedMap<String, String> m = uriInfo.getPathParameters();
		return h;
	}
}
