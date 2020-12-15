package me.violet.tags.utils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {

    String[] names();

    String permission() default "";

    String description() default "Default Description";

    String usage() default "";

    String helpTitle() default "";

    boolean playerOnly() default false;

    boolean async() default false;

}
