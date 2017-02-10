package com.example.helpdesk.logging;

import org.wildfly.swarm.config.logging.Level;

public class DVHelpdeskFormsClientLogUtil extends AbstractLogUtil{

	private static DVHelpdeskFormsClientLogUtil	instance;
	
	private DVHelpdeskFormsClientLogUtil(){
	}
	
	public static DVHelpdeskLogger getLogger() {
		return getLogger( null);
	}
	
	public static DVHelpdeskLogger getLogger( Level level) {
		if( instance == null) {
			synchronized( DVHelpdeskFormsClientLogUtil.class) {
				if( instance == null)
					instance = new DVHelpdeskFormsClientLogUtil();
			}
		}
		return instance.getLog( level == null ? Level.INFO : level);
	}
	
	@Override
	public String getLoggerName() {
		return "helpdesk-forms-client";
	}

}
