package com.shop.demo.common;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@WithSecurityContext(factory = WithAccountSecurityContextFactory.class)
public @interface WithAccount {

    long id() default 1L;

    String email() default "email@email.com";

    String name() default "김이름";

    String password() default "password";

    String[] roles() default {"CUSTOMER"};

    String[] authorities() default {};

}
