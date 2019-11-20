package com.muyan.springboot.myspringtransaction.chg.utils;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanTransction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * @ClassName MuYanAopAspect
 * @Description 采用aop来给自定义注解赋予意义
 * @Author dym
 * @Date 2019/8/30 10:41
 * @Version 1.0
 **/
@Component
@Aspect
public class MuYanAopAspect {

    //注入获取连接
    @Autowired
    private MuYanTransctionHolder muYanTransctionHolder;

    //注入事务管理
    @Autowired
    private MuYanTransctionManager muYanTransctionManager;


    /**
     *@Description  说明：
     * 1、 使用@Around环绕执行  执行前和执行后
     * 2、 采用@annotation 使用了MuyanTransction注解的类执行此方法
     * 3、 获取连接，这样保证被MuyanTransction注解的方法采用了同一个连接connection
     * 4、 同一个connection共有一个事务，如果失败，则全部回滚
     *@Author dym
     *@Date 2019/8/30 14:39
     *@Param [proceedingJoinPoint]
     *@return void
     */
    @Around( value = "@annotation(muyanTransction)" )
    public Object txAroundOption(ProceedingJoinPoint proceedingJoinPoint, MuyanTransction muyanTransction){
        //开始获取连接
        Connection connection = muYanTransctionHolder.getConnection();

        Object proceed = null;

        try{
            //开始执行前开启事务管理
            muYanTransctionManager.startTx( connection );
            //开始执行操作
            proceed = proceedingJoinPoint.proceed();

            //执行之后提交事务
            muYanTransctionManager.commitTx( connection );

        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            muYanTransctionManager.rollBackTx( connection );
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            //回滚事务
            muYanTransctionManager.rollBackTx( connection );
        }finally {
            //关闭事务
            muYanTransctionManager.closeTx( connection );
        }

        return proceed;

    }


}
