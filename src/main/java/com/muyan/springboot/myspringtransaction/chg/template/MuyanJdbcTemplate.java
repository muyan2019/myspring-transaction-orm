package com.muyan.springboot.myspringtransaction.chg.template;

import com.muyan.springboot.myspringtransaction.chg.common.InvokeClassAdapter;
import com.muyan.springboot.myspringtransaction.chg.utils.MuYanTransctionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @ClassName MuyanJdbcTemplate
 * @Description 具体执行数据库操作
 * @Author dym
 * @Date 2019/8/30 14:48
 * @Version 1.0
 **/
@Component
public class MuyanJdbcTemplate<T> {

    @Autowired
    private MuYanTransctionHolder muYanTransctionHolder;

    @Autowired
    private InvokeClassAdapter invokeClassAdapter;

    /**
     *@Description  执行具体的操作（查询）,并且返回实体类
     *@Author dym
     *@Date 2019/9/2 17:50
     *@Param [sql]
     *@return T
     */
    public <T> T executeQueryStatement(StringBuffer sql, Class<T> clazz, List<Object> list) throws IllegalAccessException, InstantiationException {
        T instance = null;
        Connection connection = muYanTransctionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement( sql.toString() );
            System.out.println( "查询sql="+sql.toString() );
            if (null != list && list.size() > 0){
                for (int i= 0; i<list.size(); i++){
                    preparedStatement.setObject( i+1, list.get( i ) );
                    System.out.println( "参数"+i+":"+list.get( i ) );
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                instance = (T) invokeClassAdapter.invokeObject( resultSet, clazz );
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     *@Description  查询结果返回集合
     *@Author dym
     *@Date 2019/9/3 17:41
     *@Param [sql, entity]
     *@return java.util.List<T>
     */
    public List<T>  executeQueryStatementByList(StringBuffer sql, Class<T> entity) throws IllegalAccessException, InstantiationException {
        T instance = null;
        List<T> instanceList = new ArrayList<>(  );
        Connection connection = muYanTransctionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement( sql.toString() );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                instance = (T) invokeClassAdapter.invokeObject( resultSet, entity );
                instanceList.add( instance );
            }
            return instanceList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }






    /**
     *@Description  执行具体的操作(增，改)
     *@Author dym
     *@Date 2019/9/2 17:04
     *@Param [sql, paramsList]
     *@return boolean
     */
    public boolean executeStatement(StringBuffer sql, List<Object> paramsList){
        Connection connection = muYanTransctionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement( sql.toString() );
            if (paramsList != null && paramsList.size()  > 0){
                for(int i = 0; i < paramsList.size(); i++){
                    preparedStatement.setObject( i+1, paramsList.get( i ));
                }
            }
            boolean execute = preparedStatement.execute();
            if (execute ){
                return Boolean.TRUE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }finally {
            //因为此处和AOP中使用的connection是同一个，所以此处不能关闭connection,只关闭preparedstatement即可
            try {
                preparedStatement.close();

                //connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Boolean.FALSE;
    }
}
