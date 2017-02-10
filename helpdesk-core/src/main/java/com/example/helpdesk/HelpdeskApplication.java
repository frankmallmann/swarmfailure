package com.example.helpdesk;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;

import com.example.helpdesk.services.ConversationRestService;
import com.example.helpdesk.services.TimeMeasurement;

/**
 * Executes configuration functions and handles submodules to the rest path
 * .../rest/
 * 
 * @author fmal
 *
 */


@ApplicationPath( "/rest")
@TimeMeasurement
public class HelpdeskApplication extends Application {

	public HelpdeskApplication() {
		
	}
	
	/**
	 * Adds submodules to rest path .../rest/ of application.
	 */
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> list = new HashSet<Class<?>>();
		list.add( ConversationRestService.class );
		return list;
	}
	
	public static void main( String... args) throws Exception {
		Swarm swarm = new Swarm( args);
		swarm.start();
		System.out.println( "Start helpdesk");
		JAXRSArchive deployment = swarm.createDefaultDeployment().as( JAXRSArchive.class).setContextRoot( "helpdesk-core");
		deployment.addAllDependencies();

		deployment.addPackages( true, "com.example.helpdesk");
		deployment.addPackages( true, HelpdeskApplication.class.getPackage());
		deployment.addPackages( true, LoggingFraction.class.getPackage());
//		deployment.addAsLibraries( new File( "C:/repository/com/fasterxml/classmate/1.3.0/classmate-1.3.0.jar"));
		swarm.deploy( deployment);
//		try {
//			StartContainer.registerAppAtGateway();
//		} catch( Exception e) {
//			DVCaseMngmtLogUtil.getLogger().warn( "Could not register to http gateway app!");
//		}
	}
}

