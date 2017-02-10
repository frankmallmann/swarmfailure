package com.example.helpdesk;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.example.helpdesk.services.TimeMeasurement;

@TimeMeasurement
public class HelpdeskAppContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}
}
