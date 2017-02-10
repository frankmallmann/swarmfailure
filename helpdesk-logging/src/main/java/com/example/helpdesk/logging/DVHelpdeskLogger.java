package com.example.helpdesk.logging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.config.logging.PeriodicSizeRotatingFileHandler;
import org.wildfly.swarm.logging.LoggingFraction;


public class DVHelpdeskLogger {
	private Logger			log;
	private String			logName;
	
	private Level										currentLevel;
	private List<PeriodicSizeRotatingFileHandler<?>>	handlers;
	private LoggingFraction fraction;

	
	protected DVHelpdeskLogger (Level level, String logName){
		this.logName = logName;
		handlers = new ArrayList<PeriodicSizeRotatingFileHandler<?>>();
		fraction = initFraction( level, logName);
		log = Logger.getLogger( logName);
		currentLevel = level;
	}
	
	private LoggingFraction initFraction( Level level, String logName) {
		return new LoggingFraction().addLoggingApiDependencies( true).periodicSizeRotatingFileHandler( "logclient-handler", ( h) -> {
			h.level( level).suffix( ".yyyy-MM-dd").rotateSize( "10m").enabled( true).encoding( "UTF-8").maxBackupIndex( 5);
			h.file( "path", "log" + File.separator + logName + ".log").autoflush( true);
			handlers.add( h);
		}).logger( logName, ( l) -> {
			l.level( level).handler( "logclient-handler").useParentHandlers( true);
		}).rootLogger( Level.INFO, "logclient-handler");
	}
	
	public String getLogName() {
		return this.logName;
	}
	
	// TODO: implement me
	public boolean isDebug() {
		return Level.DEBUG.compareTo(currentLevel) <= 0;
	}
	
	// TODO: implement me
	public boolean isTrace() {
		return false;
	}
	
	public void debug( Object msg) {
		log.debug( msg);
	}

	public void info( Object msg) {
		log.info( msg);
	}
	
	public void trace( Object msg) {
		log.trace( msg);
	}

	public void error( Object msg) {
		log.error( msg);
	}

	public void warn( Object msg) {
		log.warn( msg);
	}

	public void warn( Object msg, Throwable t) {
		log.warn( msg, t);
	}

	public void error( Object msg, Throwable t) {
		log.error( msg, t);
	}

	public Logger getInternalLogger() {
		return log;
	}
	
	/**
	 * This methods calls the toString() method of each elements
	 * and returns the result in a comma separated string
	 * @param set Set of values to convert to a String
	 * @return comma separated string with all toString() values
	 */
	public String listList( List<?> set) {
		String r = "";
		for( Object o : set)
			r += ", " + o;
		if( r.startsWith( ", "))
			return r.substring( 2);
		return r;
	}
	
	public Level getLogLevel() {
		return currentLevel;
	}

	public LoggingFraction getFraction() {
		return fraction;
	}
}
