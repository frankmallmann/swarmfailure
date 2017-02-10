package com.example.helpdesk.logging;

import org.wildfly.swarm.config.logging.Level;

public class DVHelpdeskLogUtil extends AbstractLogUtil{

	private static DVHelpdeskLogUtil 			instance;
	
	private DVHelpdeskLogUtil(){
	}
	
	public static DVHelpdeskLogger getLogger(){
		return getLogger( null );
	}
	
	public static DVHelpdeskLogger getLogger( Level level) {
		if( instance == null) {
			synchronized( DVHelpdeskLogUtil.class) {
				if( instance == null)
					instance = new DVHelpdeskLogUtil();
			}
		}
		return instance.getLog( level == null ? Level.DEBUG : level);
	}
	
	@Override
	public String getLoggerName() {
		return "helpdesk-core";
	}

	
	
}
