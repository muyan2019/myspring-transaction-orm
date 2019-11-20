package com.muyan.springboot.myspringtransaction.chg.common;

import com.muyan.springboot.myspringtransaction.chg.template.MuyanJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.*;

/**
 * @ClassName BaseMapper
 * @Description 定义抽象类，用来封装ORM数据映射
 * @Author dym
 * @Date 2019/9/2 9:43
 * @Version 1.0
 **/
public class BaseMapper<T> {

    @Autowired
    private BuildParamToFieldManager<T> buildInsertFieldParams;

    @Autowired
    private MuyanJdbcTemplate<T> muyanJdbcTemplate;


    /**
     *@Description  保存实体
     *@Author dym
     *@Date 2019/9/2 9:52
     *@Param [t]
     *@return boolean
     */
    public boolean add(T t){

        //定义sql
        StringBuffer sql = new StringBuffer(  );

        //参数集
        List<Object> paramsList = new ArrayList<>(  );

        //开始拼接单表新增的sql（Object->Field）
        buildInsertFieldParams.buildInsertFieldParams(t, sql, paramsList);

        //开始执行具体的操作
        boolean b = muyanJdbcTemplate.executeStatement( sql, paramsList );

        return b;

    }


    /**
     *@Description  删除某个实体
     *@Author dym
     *@Date 2019/9/2 9:54
     *@Param [id]
     *@return void
     */
    public boolean delete(Object id){
        //定义sql
        StringBuffer sql = new StringBuffer(  );

        //参数集
        List<Object> paramsList = new ArrayList<>(  );

        //开始拼接sql(Object->Field)
        buildInsertFieldParams.buildDeleteFieldParams(buildInsertFieldParams.getClassInfo(this.getClass()), sql, paramsList, id);

        //执行sql操作
        boolean b = muyanJdbcTemplate.executeStatement( sql, paramsList );

        return b;
    }


    /**
     *@Description  编辑某个实体
     *@Author dym
     *@Date 2019/9/2 9:54
     *@Param [t]
     *@return void
     */
    public boolean update(T t){
        //定义sql
        StringBuffer sql = new StringBuffer(  );

        //参数集
        List<Object> paramsList = new ArrayList<>(  );

        //开始拼接编辑的sql(Object->Field)
        buildInsertFieldParams.buildUpdateFieldParams(t, sql, paramsList);

        //执行sql操作
        boolean b = muyanJdbcTemplate.executeStatement( sql, paramsList );

        return b;
    }


    /**
     *@Description  查询某个实体
     *@Author dym
     *@Date 2019/9/2 9:55
     *@Param [id]
     *@return T
     */
    public T query( Object id){
        //定义sql
        StringBuffer sql = new StringBuffer(  );

        //定义参数列表
        List<Object> paramList = new ArrayList<>(  );

        //获取当前类的实体类
        Class<T> classInfo = buildInsertFieldParams.getClassInfo(this.getClass());

        try {
            //开始拼接参数（Object->Field）
            buildInsertFieldParams.buildSelectFieldParams( classInfo.newInstance(), sql, id, paramList);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        T entity = null;
        try {
            //执行查询具体操作，并且将（sql->Object）,最终将查询结果再次反转化为Object返回
            entity = muyanJdbcTemplate.executeQueryStatement( sql , classInfo, paramList );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     *@Description  查询所有数据
     *@Author dym
     *@Date 2019/9/2 9:55
     *@Param []
     *@return java.util.List<T>
     */
    public List<T> queryAll(){
        //定义sql
        StringBuffer sql = new StringBuffer(  );

        //定义参数列表
        List<Object> paramList = new ArrayList<>(  );

        //获取当前类的实体类
        Class<T> classInfo = buildInsertFieldParams.getClassInfo(this.getClass());

        try {
            //开始拼接查询的sql（Object->Field）
            buildInsertFieldParams.buildSelectFieldParams(classInfo.newInstance(), sql, null, paramList);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<T> list = null;
        try {
            //执行具体的操作，并且最终将数据库中查询出来的数据封装成实体类的集合返回
            list = muyanJdbcTemplate.executeQueryStatementByList( sql , classInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return list;
    }

}
