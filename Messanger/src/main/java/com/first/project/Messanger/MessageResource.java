package com.first.project.Messanger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.first.project.Messanger.model.Message;
import com.first.project.Messanger.services.MessageService;
import com.paramAnnotation.beans.ParamBeans;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	@GET
	 public List<Message> getAllMessages(@BeanParam ParamBeans pbins){
		if(pbins.getYear() >0) {
			return MessageService.getMessagesForYear(pbins.getYear());
			
		}
		if(pbins.getStart()>=0 && pbins.getSize()>0) {
			return MessageService.getAllMessagesPaginated(pbins.getStart(), pbins.getSize());
		}	
		return MessageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public List<Message> getMessage(@PathParam("messageId") long id) {
		return MessageService.getMessage(id);
	}

	@POST
	public List<Message> postMessage(Message msg) throws SQLException {
		return MessageService.postMessage(msg);
	}

	@PUT
	@Path("/{messageId}")
	public List<Message> updateMessage(@PathParam("messageId") long id, Message msg) {
		// return "test";
		return MessageService.updateMessage(msg, id);
	}

	@DELETE
	@Path("/{messageId}")
	public static String deleteMessaage(@PathParam("messageId") long id) {
		return MessageService.deleteMessage(id);
	}

}
