package com.muyan.springboot.myspringtransaction.chg.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName MuYanTransctionManager
 * @Description 管理事务的开发，回滚，关闭
 * @Author dym
 * @Date 2019/8/30 10:32
 * @Version 1.0
 **/
@Component
public class MuYanTransctionManager {

    //开始事务
    public void startTx(Connection connection){
        try {
            if (connection != null){
                connection.setAutoCommit( Boolean.FALSE );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public void commitTx(Connection connection){
        try {
            if (connection != null){
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //事务回滚
    public void rollBackTx(Connection connection){
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭事务
    public void closeTx(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
