package com.example.helpdesk.services;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
/**
 * Abstract implementation of a rest client. Implements a basic configuration
 * used for rest communication.
 * 
 * @author fmal
 *
 */
@ApplicationScoped
public abstract class AbstractRestClient {

	protected static Map<String, String>	restServerConfig;
	private static Logger					logger		= null;
	public static final String				LOGIN_TOKEN	= "loginToken";
	protected static final String			JSON		= MediaType.APPLICATION_JSON;
	protected static final String			PLAIN_TEXT	= MediaType.TEXT_PLAIN;
	@Inject

	protected AbstractRestClient() {
	}

	@PostConstruct
	protected void postConstruct(){
		basicInit();
	}
		
	private void basicInit() {
		
	}


	/**
	 * Appends additional path values used as standard path. This is
	 * automatically called upon object creation. Implementation can be empty if
	 * standard path shall be used.
	 * 
	 * @param currentPath
	 *            The current base path
	 * @return the complete path
	 */
	protected abstract String appendToPath(String currentPath);

	
	
	
	
}
