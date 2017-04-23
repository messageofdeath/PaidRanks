package me.messageofdeath.PaidRanks.Utils.zRequired.Commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	String name();

	String permission() default "noPerm";
}
