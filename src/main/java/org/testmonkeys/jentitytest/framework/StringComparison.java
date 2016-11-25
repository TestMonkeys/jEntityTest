package org.testmonkeys.jentitytest.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface StringComparison {
    boolean caseSensitive() default true;
    boolean trim() default false;
    char[] ignoreCharacters() default {};
}