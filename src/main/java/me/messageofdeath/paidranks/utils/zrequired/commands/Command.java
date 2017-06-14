package me.messageofdeath.paidranks.utils.zrequired.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	
	String name();

	String permission() default "noPerm";
}
