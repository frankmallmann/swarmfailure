package com.example.helpdesk.services;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.example.helpdesk.HelpdeskApplication;
import com.example.helpdesk.logging.DVHelpdeskLogUtil;

@RunWith(Arquillian.class)
//@DefaultDeployment
public class ConversationRestServiceIT {

		
//	@ArquillianResource
//	InitialContext context;
	
	@Deployment
    public static Archive<?> createDeployment() {
		try {
			JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class).setContextRoot( "helpdesk-core");
			deployment.addAllDependencies();
			deployment.addPackages( true, "com.example.helpdesk");
			deployment.addPackages( true, HelpdeskApplication.class.getPackage());
			deployment.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	        return deployment;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
//	@CreateSwarm
//    public static Swarm newContainer() throws Exception {
//        Swarm swarm = new Swarm();
//        return swarm;
//    }
	
	@Test
	public void testAnything(){
		DVHelpdeskLogUtil.getLogger().debug("Yeyy successful!");
		System.out.println("Yeyy successful!");
	}

}
