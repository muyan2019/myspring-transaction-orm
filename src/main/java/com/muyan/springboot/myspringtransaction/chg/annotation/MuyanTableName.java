package com.muyan.springboot.myspringtransaction.chg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *@Description  自定义注解，用来表示类和数据库表的关系
 *@Author dym
 *@Date 2019/8/30 15:05
 *@Param
 *@return
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MuyanTableName {

    String tableName() default "";
}
