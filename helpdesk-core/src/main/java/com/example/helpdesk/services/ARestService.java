package com.example.helpdesk.services;

import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@ApplicationScoped
public abstract class ARestService {

	public static final String JSON = MediaType.APPLICATION_JSON;
	protected boolean isDebugLog;
	
	private final static String DEFAUTL_CHARSET = "UTF-8";
	private final static String CHARSET = "charset";
	private final static String CONTENT_TYPE = "Content-Type";
	
	private static final String LOGIN_TOKEN = "loginToken";

	public ARestService() {
	}

	/**
	 * Adds headers to allow JavaScript to invoke this rest service
	 * 
	 * @param r
	 *            the response to add custom headers
	 * @return the input parameter, now containing custom headers
	 */
	protected Response addHeaders(Response r, HttpServletRequest servletRequest) {
		r.getHeaders().add("Access-Control-Allow-Origin", "*");
		r.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		r.getHeaders().add("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0"); // HTTP
																								// 1.1.
		r.getHeaders().add("Pragma", "no-cache"); // HTTP 1.0.
		r.getHeaders().add("Expires", 0);
		final MediaType contentTypeSpecifiedByService = r.getMediaType();
		if (contentTypeSpecifiedByService != null
				&& !contentTypeSpecifiedByService.getParameters().containsKey(CHARSET)) {
			final MediaType mediaTypeWithCharset = new MediaType(contentTypeSpecifiedByService.getType(),
					contentTypeSpecifiedByService.getSubtype(), Collections.singletonMap(CHARSET, DEFAUTL_CHARSET));
			r.getHeaders().putSingle(CONTENT_TYPE, mediaTypeWithCharset);
		}

		return r;
	}

	protected Cookie getLoginCookie(HttpServletRequest request) {
		if (request == null)
			return null;
		Cookie[] cookies = request.getCookies();
		Object oCookie = request.getAttribute(LOGIN_TOKEN);
		if (cookies == null) {
			if (oCookie != null && oCookie instanceof Cookie)
				return (Cookie) oCookie;
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(LOGIN_TOKEN)) {
					return cookie;
				}
			}
		}
		return null;
	}

	protected String getLoginToken(HttpServletRequest request) {
		Cookie cookie = getLoginCookie(request);
		return cookie == null ? null : cookie.getValue();
	}

	protected Response getBadRequestResponse( HttpServletRequest request, String msg) {
		return addHeaders( Response.status( Status.BAD_REQUEST).entity( msg).build(), request);
	}
	
	protected Response getNoContentResponse( HttpServletRequest request) {
		return addHeaders( Response.status( Status.NO_CONTENT).build(), request);
	}
}
