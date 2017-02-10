package com.example.helpdesk.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path( "conversation" )
@ApplicationScoped
@TimeMeasurement
public class ConversationRestService extends ARestService{
	@Inject
	private ConversationClientImpl conversationClient;
	
	@POST
	@Consumes( JSON)
	@Produces( JSON)
	@Path( "add/")
	public Response addConversation( String jsonRequest, @Context HttpServletRequest servletRequest, @Context final HttpServletResponse servletResponse) {
		
		// Mein Rest Service wird ohne Login vom Hook aufgerufen und kümmert sich dann um den Call an den Case Manager
		// und um den entsprechenden Login.
		Boolean response = conversationClient.createConversationAndAddMessage(jsonRequest);
		
		return addHeaders( Response.ok().build(), servletRequest);
	}
}
