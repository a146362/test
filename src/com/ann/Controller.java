package com.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 *
 */
/*Retention：生命周期
 *RetentionPolicy.RUNTIME ---> 始终存在*/
@Retention(RetentionPolicy.RUNTIME)
/*ElementType.TYPE:只能放在类中
ElementType.METHOD:能在方法中使用
* Target：注解能在哪里使用*/
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Controller {
}
