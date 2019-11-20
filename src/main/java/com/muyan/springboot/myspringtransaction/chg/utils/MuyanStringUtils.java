package com.muyan.springboot.myspringtransaction.chg.utils;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanColumn;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanId;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanTableName;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @ClassName MuyanStringUtils
 * @Description 自定义工具类
 * @Author dym
 * @Date 2019/9/3 9:39
 * @Version 1.0
 **/
public class MuyanStringUtils {


    /**
     *@Description  将第一个字母替换为大写
     *@Author dym
     *@Date 2019/9/3 9:40
     *@Param [str]
     *@return java.lang.String
     */
    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    /**
     *@Description  获取属性的set方法
     *@Author dym
     *@Date 2019/9/3 9:40
     *@Param [fieldName]
     *@return java.lang.String
     */
    public static String getSetMethod(String fieldName){
        return "set"+firstUpperCase( fieldName );
    }

    /**
     *@Description  根据实体获取表名称
     *@Author dym
     *@Date 2019/9/2 15:26
     *@Param [clazz]
     *@return java.lang.String
     */
    public static String getTableName(Class<?> clazz){
        //默认获取类中的小写名称
        String tableName  = clazz.getSimpleName();
        if (null != clazz){
            //判断类是否加载了自定义注解
            if (clazz.isAnnotationPresent( MuyanTableName.class)){
                MuyanTableName muyanTableName = clazz.getAnnotation( MuyanTableName.class );
                tableName = muyanTableName.tableName();

            }
        }
        return tableName;
    }

    /**
     *@Description  根据属性名称获取字符名称
     *@Author dym
     *@Date 2019/9/2 15:28
     *@Param [field]
     *@return java.lang.String
     */
    public static String getTableFieldName(Field field){
        String fieldName = "";
        if (null != field){
            if (field.isAnnotationPresent( MuyanColumn.class )){
                MuyanColumn annotation = field.getAnnotation( MuyanColumn.class );
                if (!Objects.equals("",annotation)){
                    fieldName  = annotation.colomnName();
                }
            }else if(!field.isAnnotationPresent( MuyanId.class )){
                fieldName = field.getName();
            }
        }
        return fieldName;
    }

    /**
     *@Description  根据字段获取主键的名称
     *@Author dym
     *@Date 2019/9/2 17:17
     *@Param [field]
     *@return java.lang.String
     */
    public static String getTableIdName(Field field){
        String idName = "";
        if (null != field){
            if (field.isAnnotationPresent( MuyanId.class )){
                MuyanId muyanId = field.getAnnotation( MuyanId.class );
                if (!Objects.equals( "",muyanId )){
                    idName = muyanId.idName();
                }

            }else if (!field.isAnnotationPresent( MuyanColumn.class )){
                idName = field.getName();
            }
        }
        return idName;

    }
}
