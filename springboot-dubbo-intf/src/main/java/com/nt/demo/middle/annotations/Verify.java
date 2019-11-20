package com.nt.demo.middle.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author  by TaoTaoNing
 * 2019-11-19
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Verify {

    /** 参数名称 */
    String name();

    /** 参数最大长度 */
    int maxLength() default Integer.MAX_VALUE;

    /** 是否必填 */
    boolean required() default false;

    /** 最小长度 */
    int minLength() default Integer.MIN_VALUE;

    /** 正则匹配 */
    String regular() default "";

}
