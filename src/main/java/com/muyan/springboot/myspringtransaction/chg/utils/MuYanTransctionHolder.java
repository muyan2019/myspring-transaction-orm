package com.muyan.springboot.myspringtransaction.chg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * @ClassName MuYanTransctionHolder
 * @Description 自定义一个事务管理的工具类
 * @Author dym
 * @Date 2019/8/30 10:20
 * @Version 1.0
 **/
@Component   //交给spring去管理
public class MuYanTransctionHolder {

    //本来线程不安全的，通过ThreadaLocal这么封装一下，立刻就变成了线程的局部变量，不仅仅安全了，
    // 还保证了一个线程下面的操作拿到的Connection是同一个对象
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    @Autowired
    private MuyanDataSource muyanDataSource;

    //获取连接
    public Connection getConnection(){
        try {
            if (threadLocal.get() == null){
                Connection connection = muyanDataSource.getConnection();
                threadLocal.set( connection );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return threadLocal.get();

    }
}
