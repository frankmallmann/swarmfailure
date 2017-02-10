package com.example.helpdesk.services;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.annotation.Priority;
import javax.interceptor.InterceptorBinding;

@Retention( RUNTIME)
@Target( {TYPE, METHOD})
@InterceptorBinding
@Inherited
@Priority( javax.interceptor.Interceptor.Priority.APPLICATION)
public @interface TimeMeasurement {

}

