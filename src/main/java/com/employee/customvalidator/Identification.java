package com.employee.customvalidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.metadata.MethodType;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=IdValiadtor.class)
public @interface Identification {
	
String message() default "Id is not valid";
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
