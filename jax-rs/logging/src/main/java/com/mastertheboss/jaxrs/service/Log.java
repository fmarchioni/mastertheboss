package com.mastertheboss.jaxrs.service;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.ws.rs.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Log { }