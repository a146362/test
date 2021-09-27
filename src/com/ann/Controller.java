package com.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 *
 */
/*Retention����������
 *RetentionPolicy.RUNTIME ---> ʼ�մ���*/
@Retention(RetentionPolicy.RUNTIME)
/*ElementType.TYPE:ֻ�ܷ�������
ElementType.METHOD:���ڷ�����ʹ��
* Target��ע����������ʹ��*/
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Controller {
}
