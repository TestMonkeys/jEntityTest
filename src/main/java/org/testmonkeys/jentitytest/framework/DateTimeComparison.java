package org.testmonkeys.jentitytest.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DateTimeComparison {

    int delta() default 0;

    ChronoUnit unit() default ChronoUnit.SECONDS;
}
