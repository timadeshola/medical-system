package com.oze.medicalsystem.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 2:27 PM
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface StaffValidation {

}
