package com.example.helpdesk.services;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@TimeMeasurement
@Priority( javax.interceptor.Interceptor.Priority.APPLICATION)
public class TimeMeasurementInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6197238031002895445L;

	@AroundInvoke
	public Object timeMeasurement( InvocationContext ictx) throws Exception {
		
			
			Object result = ictx.proceed();
			System.out.println("Intercepting");
			return ictx.proceed();
		
	}
}