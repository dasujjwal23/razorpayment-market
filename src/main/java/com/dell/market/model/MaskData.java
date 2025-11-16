package com.dell.market.model;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JacksonAnnotationsInside
@JsonSerialize(using=ProtectDataSerializer.class)
@Retention(RUNTIME)
@Target(METHOD)
public @interface MaskData {

}
