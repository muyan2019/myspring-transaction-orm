package com.muyan.springboot.myspringtransaction.chg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Description  自定义字段和属性的映射关系注解
 *@Author dym
 *@Date 2019/8/30 15:07
 *@Param
 *@return
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface MuyanColumn {

    String colomnName() default "";
}
