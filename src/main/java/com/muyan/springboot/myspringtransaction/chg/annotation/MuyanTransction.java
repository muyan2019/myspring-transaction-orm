package com.muyan.springboot.myspringtransaction.chg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName MuyanTransction
 * @Description 自定义一个需要事务管理的注解
 * @Author dym
 * @Date 2019/8/30 10:17
 * @Version 1.0
 **/
@Target( ElementType.METHOD )
@Retention(RetentionPolicy.RUNTIME)
public @interface MuyanTransction {
}
