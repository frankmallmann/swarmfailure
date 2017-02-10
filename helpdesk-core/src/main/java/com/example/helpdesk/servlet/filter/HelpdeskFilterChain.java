package com.example.helpdesk.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HelpdeskFilterChain implements Filter{

	private LoginFilterHelpdesk 				loginFilterHelpdesk;
	private MaintenanceFilterHelpdesk 			maintenanceFilterHelpdesk;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.loginFilterHelpdesk = new LoginFilterHelpdesk();
		this.maintenanceFilterHelpdesk = new MaintenanceFilterHelpdesk();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if( !response.isCommitted() && !((HttpServletRequest) request).getRequestURI().endsWith( "maintenance.html")){
			maintenanceFilterHelpdesk.doFilter(request, response, chain);
		}
		if( !response.isCommitted() && !((HttpServletRequest) request).getRequestURI().endsWith( "login.html")){
			loginFilterHelpdesk.doFilter(request, response, chain);
		}
		
		if( !response.isCommitted())
			chain.doFilter( request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
