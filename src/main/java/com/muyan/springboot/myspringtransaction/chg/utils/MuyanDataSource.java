package com.muyan.springboot.myspringtransaction.chg.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName MuyanDataSource
 * @Description 数据源定义
 * @Author dym
 * @Date 2019/9/4 9:07
 * @Version 1.0
 **/
@Component
@PropertySource( value = "classpath:/application.properties")
public class MuyanDataSource {

    @Value( "${spring.datasource.driver}" )
    public  String driver ;

    @Value( "${spring.datasource.url}" )
    public String url ;

    @Value( "${spring.datasource.username}" )
    public String username;

    @Value( "${spring.datasource.password}" )
    public String password ;

    //加载驱动

    @PostConstruct
    public void initDriver(){
        try {
            Driver drivers = (Driver) Class.forName( driver ).newInstance();
            // 把这个驱动之家注册到驱动管理器内
            DriverManager.registerDriver( drivers );
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     *@Description  获取连接
     *@Author dym
     *@Date 2019/9/4 9:37
     *@Param []
     *@return java.sql.Connection
     */
    public Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection( url, username, password );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
