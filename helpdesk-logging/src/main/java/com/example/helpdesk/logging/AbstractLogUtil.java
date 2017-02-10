package com.example.helpdesk.logging;

import org.wildfly.swarm.config.logging.Level;

public abstract class AbstractLogUtil {

	protected DVHelpdeskLogger	log;
	
	protected AbstractLogUtil() {
	}
	
	protected final DVHelpdeskLogger getLog( Level level) {
		if( log == null) {
			log = new DVHelpdeskLogger( level, getLoggerName());
		}
		return log;
	}
	
	public abstract String getLoggerName();
}
