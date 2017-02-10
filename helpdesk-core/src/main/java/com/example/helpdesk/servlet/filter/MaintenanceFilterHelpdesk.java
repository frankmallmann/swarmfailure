package com.example.helpdesk.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaintenanceFilterHelpdesk implements Filter {
	private static final String	HELPDESK_CORE		= "/helpdesk-core";
	private static final String	MAINTENANCE			= "/maintenance.html";
	private static final String	CONFIGURATION		= "/config.html";
	private static final String	ADMINISTRATION		= "/administration.html";
	private static final String	DB_UPDATE			= "/dbUpdate.html";
	
	private boolean				didRedirect;
	private boolean				maintenanceEnabled	= false;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if( maintenanceEnabled) {
			didRedirect = redirect( request, response);
			if( didRedirect)
				return;
		}
	}
		
	private boolean redirect( ServletRequest request, ServletResponse response) throws IOException {
		if( request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			if( isException( req.getRequestURI()))
				return false;
			String service = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + HELPDESK_CORE;
			String maintenance = service + MAINTENANCE;
			if( req.getRequestURL().toString().startsWith( maintenance)) {
				return false;
			}
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect( maintenance);
			return true;
		}
		return false;
	}
	
	private boolean isException( String uri) {
		if( uri == null )
			return false;
		return uri.contains( MAINTENANCE) || uri.contains( CONFIGURATION) || uri.contains( ADMINISTRATION) || uri.contains( DB_UPDATE) || uri.contains( "/maintenanceMode/") || uri.contains( "/lib/") || uri.contains( "/fonts/") || uri.contains( "/img/") || uri.contains( "/rest/config/displayName");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
