package com.example.helpdesk.servlet.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilterHelpdesk implements Filter {
	private static final String	HELPDESK_CORE			= "/helpdesk-core";
	private static final String	LOGIN_CHECK				= "/rest/checkLogin/";
	private static final String	LOGIN					= "/login.html";
	
	public static final String	REDIRECT_TOKEN			= "redirectToken";
	
	private boolean				didRedirect;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if( !hasToken( request)) {
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
			String checkLogin = service + LOGIN_CHECK;
			if(req.getRequestURL().toString().startsWith( checkLogin)) {
				return false;
			}
			String createConversation = service + "/rest/conversation/add";
			if(req.getRequestURL().toString().startsWith( createConversation)) {
				return false;
			}
			
			String target = service + LOGIN;
			String reqURI = req.getRequestURI();
			reqURI = "/case-mngmnt-core";
			target += "?" + REDIRECT_TOKEN + "=" + URLEncoder.encode(  reqURI + "?" + req.getQueryString(), "UTF-8");
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect( target);
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	public static boolean hasToken( ServletRequest request) {
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if( cookies != null) {
			for( Cookie c : cookies) {
				if( c.getName().equals( "loginToken")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isException( String uri) {
		if( uri == null )
			return false;
		return uri.contains( LOGIN) || uri.contains( "/login") || uri.endsWith( "/maintenance.html") || uri.contains( "/rest/config")  || uri.endsWith( "/maintenanceMode/saveLoginAdminConfiguration") || uri.contains( "/rest/conditions/importOfDocument/") || uri.contains( "/rest/conditions/updateOfDocument/") || uri.contains( "/conversation/importOfMail/") || uri.contains( "/lib/") || uri.contains( "/fonts/");
	}
}
