package org.testmonkeys.jentitytest.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ValidateRegex {
    String expression() default "";

    RegexMatching matching() default RegexMatching.Strict;

    NullHandling nullHandling() default NullHandling.Fail;

    enum RegexMatching {
        Strict
    }

    enum NullHandling {
        Fail,
        Pass
    }

}
